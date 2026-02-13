import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_2468 {

    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        int maxHeight = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, arr[i][j]);
            }
        }

        int answer = 0;

        // 🔥 반드시 0부터 시작
        for (int h = 0; h <= maxHeight; h++) {

            visited = new boolean[N][N];
            int count = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    // 안 잠기고, 아직 방문 안 했으면
                    if (!visited[i][j] && arr[i][j] > h) {
                        bfs(i, j, h);
                        count++;
                    }
                }
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    public static void bfs(int r, int c, int h) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];

            for (int i = 0; i < 4; i++) {

                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                    continue;

                if (!visited[nr][nc] && arr[nr][nc] > h) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }
}
