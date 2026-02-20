import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class baekjoon_2636 {
    static int N, M;
    static int[][] arr;

    static final int WALL = 2, OUTSIDE = 3, CHEESE = 1, MELT = 4;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1]; 

        int cnt = 0;
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(i == 1 || j == 1 || i == N || j == M) {
                    arr[i][j] = WALL;
                }
                if(arr[i][j] == 1) cnt++;
            }
        }

        int sec = 0;
        if(cnt == 0) {
            System.out.println(sec);
            System.out.println(0);
            return;
        }

        // findOutside();
        // printMap("after find outside");

        // findMelt();
        // printMap("after find melt");

        while(true) {
            sec++;
            findOutside();
            int diff = findMelt();
            if(cnt - diff == 0) {
                System.out.println(sec);
                System.out.println(diff);
                break;
            }
            else {
                cnt -= diff;
            }
        }
        
    }

    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void findOutside() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][M+1];
        
        queue.add(new int[]{1, 1});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            
            for(int i=0;i<4;i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 1 || nc < 1 || nr > N || nc > M) continue;
                if(visited[nr][nc] || arr[nr][nc] == CHEESE) continue;

                if(arr[nr][nc] == 0 || arr[nr][nc] == OUTSIDE) {
                    arr[nr][nc] = OUTSIDE;
                }

                queue.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
    }

    public static int findMelt() {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) { 

                if(arr[i][j] != 1) continue;
                
                for(int k=0;k<4;k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    
                    if(arr[nr][nc] == OUTSIDE || arr[nr][nc] == WALL) {
                        // arr[i][j] = OUTSIDE;
                        queue.add(new int[]{i, j});
                        cnt++;
                        break;
                    }
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            
            arr[r][c] = OUTSIDE;
        }

        return cnt;
    }
    
    public static void printMap(String msg) {
        System.out.printf("=== %s ===\n", msg);

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }
    }
}
