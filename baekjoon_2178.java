import java.io.*;
import java.util.*;

public class baekjoon_2178

    static int N, M, MIN=Integer.MAX_VALUE;
    static boolean[][] map, visited;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++){
           char[] line = br.readLine().toCharArray();
           for(int j=0;j<M;j++){
                map[i][j] = (line[j] == '1') ? true : false;
           }           
        }

        visited[0][0] = true;
        queue.add(new int[] {0, 0, 1});     //n m sum
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int n = cur[0];
            int m = cur[1];
            int sum = cur[2];

            if (n == N - 1 && m == M - 1) {
                MIN = Math.min(MIN, sum);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int ny = n + dy[i];
                int nx = m + dx[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N || visited[ny][nx] || !map[ny][nx]) continue;

                visited[ny][nx] = true;
                queue.add(new int[]{ny, nx, sum + 1});
            }
        }
        System.out.println(MIN);
    }


        
}