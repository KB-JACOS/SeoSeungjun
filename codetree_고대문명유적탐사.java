import java.util.*;
import java.io.*;

public class codetree_고대문명유적탐사 {

    static int[][] arr = new int[6][6];
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());;

        for(int i=1;i<=5;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=5;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] wall = new int[M+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=M;i++) {
            wall[i] = Integer.parseInt(st.nextToken());
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int wp = 1;
        for(int tc = 1;tc<=N;tc++){
            List<Case> coordinate = new ArrayList<>();

            // <[1] 탐사 진행>//////////////////////////////////////////////////////////////////////////////////// 
            for(int i=2;i<5;i++) {
                for(int j=2;j<5;j++) {
                    int[][] res = copy(arr);
                    for(int k=1;k<=3;k++) {      // (k | 1=90, 2=180, 3=270)
                        res = rotate(i, j, res);
                        int sum = search(res);
                        coordinate.add(new Case(i, j, sum, k, res));
                        // System.out.printf("r=%d c=%d sum=%d k=%d\n", i,j,sum,k);
                    }
                }
            }

            Collections.sort(coordinate, (o1, o2) -> {
                if(o1.sum != o2.sum) return Integer.compare(o2.sum, o1.sum);
                if(o1.degree != o2.degree) return Integer.compare(o1.degree, o2.degree);
                if(o1.c != o2.c ) return Integer.compare(o1.c, o2.c);
                return Integer.compare(o1.r, o2.r);
            });
            
            Case searchResult = coordinate.get(0); // 탐사 결과
            if(searchResult.sum == 0) break;   
            // </[1] 탐사 진행> //////////////////////////////////////////////////////////////////////////////////// 
            
            // <[2] 유물 획득> //////////////////////////////////////////////////////////////////////////////////// 
            
            int[][] res = copy(searchResult.res);
            int total = 0;

            while(true) {
                
                // [2-1] 유물찾기
                LegacyStage stage = new LegacyStage(res, 0);
                List<int[]> removeList = getLegacy(stage);
                
                if(stage.sum < 3) break;

                // [2-2] 발굴된 유물 좌표 정렬 (채우는 순서)
                removeList.sort((o1, o2) -> {
                    if(o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
                    return Integer.compare(o2[0], o1[0]);
                });
                
                // [2-3] 발굴된 유물 좌표 채우기
                for(int[] cur : removeList) {
                    int r = cur[0];
                    int c = cur[1];
                    stage.res[r][c] = wall[wp];
                    wp++;
                }
                
                
                total += stage.sum;
                res = stage.res;
            }
            // </유물 획득> ////////////////////////////////////////////////////////////////////////////////////

            if(total == 0) { continue; }
            else           { bw.append(total + " "); }
            arr = copy(res);
        }
        bw.append("\n");
        bw.flush();
        bw.close();
    }

    // 유물 찾기
    public static List<int[]> getLegacy(LegacyStage stage) {

        List<int[]> result = new ArrayList<>();
        int[][] visited = new int[6][6];
        int id = 1;
        for(int i=1;i<=5;i++) {
            for(int j=1;j<=5;j++) {
                if(visited[i][j] != 0) continue;
                int cnt = bfsLegacy(i, j, id, stage.res, visited, result);
                if(cnt >= 3 ) stage.sum += cnt;
                id++;
            }
        }

        for(int[] a : result ) {
            stage.res[a[0]][a[1]] = 0;
        }
        return result;
    }

    public static int bfsLegacy(int r, int c, int id, int[][] res, int[][] groupMemo, List<int[]> result) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r, c});

        boolean[][] visited = new boolean[6][6];
        visited[r][c] = true;

        groupMemo[r][c] = id;

        int weight = res[r][c];
        int cnt = 1;
        List<int[]> temp = new ArrayList<>();

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            temp.add(new int[]{cur[0], cur[1]});
            for(int d=0;d<4;d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(nr < 1 || nc < 1 || nr > 5 || nc > 5 || visited[nr][nc]) { continue; }
                if(res[nr][nc] != weight) continue;

                queue.add(new int[] {nr, nc});
                
                visited[nr][nc] = true;
                groupMemo[nr][nc] = id;
                cnt++;
            }
        }
        
        if(cnt >= 3) {
            result.addAll(temp);
            return cnt;
        }

        if(result.size() == 0) return 0;
        return cnt;
    }

    public static int search(int[][] res) {
        
        int[][] visited = new int[6][6];
        int id = 1;
        int sum = 0;
        for(int i=1;i<=5;i++) {
            for(int j=1;j<=5;j++) {
                if(visited[i][j] != 0) continue;
                int cnt = bfsSearch(i, j, id, res, visited);
                if(cnt >= 3) sum += cnt;
                id++;
            }
        } 

        return sum;
    }

    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static int bfsSearch(int r, int c, int id, int[][] res, int[][] groupMemo) {

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[6][6];

        queue.add(new int[] {r, c});
        visited[r][c] = true;
        groupMemo[r][c] = id;
        int weight = res[r][c]; 
        int cnt = 1;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int d=0;d<4;d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(nr < 1 || nc < 1 || nr > 5 || nc > 5 || visited[nr][nc]) { continue; }
                
                if(res[nr][nc] != weight) continue;
                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                groupMemo[nr][nc] = id;
                cnt++;
            }
        }

        return cnt;
    }

    public static void print(String msg , int[][] arr) {
        System.out.println(msg);
        for(int i=1;i<=5;i++) {
            for(int j=1;j<=5;j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 가운데 좌표 기준으로 90도 돌리기 ㅅㅂ 존나 어렵네 ㅋㅅㅋㅅㅋㅅㅋㅅㅋㅅㅋ
    public static int[][] rotate(int r, int c, int[][] origin) {
        
        int[][] res = new int[6][6];

        for(int i=1;i<=5;i++) {
            for(int j=1;j<=5;j++) {
                res[i][j] = origin[i][j];
            }
        }

        for(int i=r-1;i<=r+1;i++) {
            for(int j=c-1;j<=c+1;j++) {
                int ii = (j - (c - 1)) + (r - 1);
                int jj = (2 - (i-(r-1)) + (c - 1));
                res[ii][jj] = origin[i][j];
            }
        }

        return res;
    }

    public static class Case {
        int[][] res;
        int sum, degree, r, c;

        public Case(int r, int c, int sum, int degree, int[][] res) {
            this.res = copy(res);
            this.sum = sum;
            this.r = r;
            this.c = c;
            this.degree = degree;
        }
    }

    public static class LegacyStage {
        int[][] res; 
        int sum; 
        
        public LegacyStage(int[][] res, int sum) {
            this.res =res;
            this.sum = sum;
        }

    }

    public static int[][] copy(int[][] origin) {
        int[][] res = new int[origin.length][origin.length];
        for(int i=0;i<origin.length;i++) {
            for(int j=0;j<origin.length;j++) {
                res[i][j] = origin[i][j];
            }
        }
        return res;
    }
}