import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(i, j, 1, map[i][j], visit);
                visit[i][j] = false;
                checkT(i, j);
            }
        }

        System.out.println(result);
    }

    static void dfs(int y, int x, int cnt, int sum, boolean[][] visit) {
        if (cnt >= 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx]) continue;

            visit[ny][nx] = true;
            dfs(ny, nx, cnt + 1, sum + map[ny][nx], visit);
            visit[ny][nx] = false;
        }
    }

    static void checkT(int y, int x) {
        if (y < N - 2 && x < M - 1)
            result = Math.max(result, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 1][x + 1]);

        if (y < N - 2 && x > 0)
            result = Math.max(result, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 1][x - 1]);

        if (y < N - 1 && x < M - 2)
            result = Math.max(result, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x + 1]);

        if (y > 0 && x < M - 2)
            result = Math.max(result, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y - 1][x + 1]);
    }
}
