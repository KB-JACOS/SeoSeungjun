import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<int[]>[][] switches;
    static boolean[][] light;     // 불 켜짐 상태
    static boolean[][] visited;   // 실제 방문 가능 여부
    static boolean[][] reachable; // 불만 켜지고 아직 방문 못 한 상태
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        switches = new ArrayList[N+1][N+1];
        light = new boolean[N+1][N+1];
        visited = new boolean[N+1][N+1];
        reachable = new boolean[N+1][N+1];

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                switches[i][j] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switches[x][y].add(new int[]{a, b});
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1});
        visited[1][1] = true;
        light[1][1] = true;

        int countLights = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            // (x, y)의 스위치를 이용해 다른 방 불 켜기
            for (int[] sw : switches[x][y]) {
                int nx = sw[0], ny = sw[1];

                if (!light[nx][ny]) {
                    light[nx][ny] = true;
                    countLights++;

                    // 새로 켜진 방이 "이미 방문했던 방의 인접"이면 바로 방문 가능
                    if (isAdjacentVisited(nx, ny)) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }

            // 4방향 이동
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 1 || ny < 1 || nx > N || ny > N) continue;

                if (light[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return countLights;
    }

    static boolean isAdjacentVisited(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
            if (visited[nx][ny]) return true;
        }
        return false;
    }
}
