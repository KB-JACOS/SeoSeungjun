    import java.util.*;
    import java.io.*;

    public class Main {

        static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1}; // 상 하 좌 우
        static int R, C, result = 0;
        static char[][] map;

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            while(true) {
                int N = Integer.parseInt(br.readLine());
                idx++;
                if(N == 0) break; 

                int[][] map = new int[N][N];
                for(int i=0;i<N;i++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());

                    for(int j=0;j<N;j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
                
                sb.append(String.format("Problem %d: %d\n", idx, find(map, N)));
            }
            
            System.out.println(sb.toString());
        }
        
        public static int find(int[][] map, int N) {
            Queue<int[]> pq = new PriorityQueue<int[]>(
                (o1, o2) -> Integer.compare(o1[2], o2[2])
            );

            int[][] dp = new int[N][N];

            for(int[] arr : dp) {
                Arrays.fill(arr, Integer.MAX_VALUE);
            }

            pq.add(new int[] {0,0, map[0][0]});

            while(!pq.isEmpty()) {

                int[] cur = pq.poll();
                int r = cur[0];
                int c = cur[1];
                int cost = cur[2];

                for(int i=0;i<4;i++) {
                    
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || dp[nr][nc] <= cost + map[nr][nc]){
                        continue;
                    }

                    dp[nr][nc] = cost + map[nr][nc];
                    pq.add(new int[]{nr, nc, cost + map[nr][nc]});
                }
            }

            return dp[N - 1][N - 1]; 
        }

}
