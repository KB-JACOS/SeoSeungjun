import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_14502 {
    static List<int[]> virus = new ArrayList<>();
    static int N, M;
    static int[][] map ;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }

        dfs(new ArrayList<int[]>(), new boolean[N][M]);

        System.out.println(max);
    }

    //bfs
    static int max = Integer.MIN_VALUE; 

    public static void dfs(List<int[]> wall, boolean[][] visited) {

        if(wall.size() >= 3) {

            int[][] temp = new int[N][M];

            //맵만들고
            for(int i=0;i<N;i++) {
                temp[i] = Arrays.copyOf(map[i], M);
            }

            // System.out.println("===== ======");
            //벽쌓고
            for(int[] w : wall) {
                
                // System.out.printf("%d %d\n", w[0], w[1]);
                temp[w[0]][w[1]] = 1;
            }

            // 바이러스별로 bfs
            for(int[] v : virus) {
                int vr = v[0];
                int vc = v[1];

                bfs(vr, vc, temp);
            }

            // 안전 영역 세기
            int cnt = 0;
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(temp[i][j] == 0) cnt++;
                }
            }

            max = Math.max(max, cnt);
            return;
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 1 || map[i][j] == 2 || visited[i][j]) continue;
                
                wall.add(new int[]{i,j});
                visited[i][j] = true;
                dfs(wall, visited);
                wall.remove(wall.size() - 1);
                visited[i][j] = false;
            }
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void bfs(int vr, int vc, int[][] mapTemp) {
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{vr, vc});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            for(int i=0;i<4;i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M || mapTemp[nr][nc] == 1 || mapTemp[nr][nc] == 2) {
                    continue;
                }
                
                mapTemp[nr][nc] = 2;
                queue.add(new int[]{nr, nc});
            }
        }
        
    }
}