import java.util.*;
import java.io.*;

public class baekjoon_16236 {
    static int N ; 
    static int[][] map;
    static int lv = 2; 
    static int exp = 0;
    static List<int[]> fish = new ArrayList<>();
    static int sr, sc = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9) {
                    sr = i;
                    sc = j;
                    map[i][j] = 0;
                    continue;
                }

                if(map[i][j] >= 1 && map[i][j] <= 6) {
                    fish.add(new int[] {i,j});
                }
            }
        }
        
        
        int cnt = 0;
        while(true) {

            int[] nextFish = getNext(sr, sc);

            if(nextFish == null) break;

            int fr = nextFish[0], fc = nextFish[1], fd = nextFish[2], fIdx = nextFish[3];
            
            //이동
            sr = fr;
            sc = fc;
            
            // 경험치 증가
            exp++;
            
            // 레벨업
            if(exp == lv) {
                lv++;
                exp = 0;
            }

            map[fr][fc] = 0;
            fish.remove(fIdx);
            cnt+= fd;
        }

        System.out.println(cnt);
    }

    /**
     * 현재 위치 기반으로 다음 물고기 구하기
     */
    
    public static int[] getNext(int r, int c) {
        // {r, c, distance, lv, idx}
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {

            //거리가 같으면, 
            if(o1[2] == o2[2]) {

                // 높이가 같다면
                if(o1[0] == o2[0]) {

                    //가장 가깝고 높고 왼쪽 물고기
                    return Integer.compare(o1[1], o2[1]);    
                }
                
                // 가장 가깝고 높은 물고기
                return Integer.compare(o1[0], o2[0]);
                
            }

            // 가장 가까운 물고기
            return Integer.compare(o1[2], o2[2]);
        });
        

        // 물고기별 도달 가능 여부 및 pq에 넣기
        for(int i=0;i<fish.size();i++) {
            int[] cur = fish.get(i);
            int fr = cur[0];
            int fc = cur[1];
            
            //먹을 수 없는 물고기는 후보 X 
            if(map[fr][fc] >= lv) {continue;}
            
            //조건에 맞게 거리 계산
            int fd = getDistance(new int[]{sr, sc}, new int[]{fr, fc});
            if(fd == -1) continue;
            
            pq.add(new int[] {fr, fc, fd, i});
        }
        
        
        // 우선순위 제일 높은애
        if(pq.isEmpty()) return null;

        return pq.poll();
    }


    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static int getDistance(int[] src, int[] dst) {
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new int[]{src[0], src[1], 0});
        visited[src[0]][src[1]] = true;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int w = cur[2];
            
            for(int i=0;i<4;i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) {
                    continue;
                }
                
                if(map[nr][nc] > lv) continue;

                if(nr == dst[0] && nc == dst[1]) {
                    return w + 1;
                }

                visited[nr][nc]= true;
                queue.add(new int[]{nr, nc, w+1});
            }
        }

        return -1;
    }
}