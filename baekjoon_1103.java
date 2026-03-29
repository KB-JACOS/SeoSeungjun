import java.io.*;
  import java.util.*;

  public class baekjoon_1103 {
      static int N, M;
      static char[][] board;
      static int[][] dp;
      static boolean[][] visited;
      static int[] dx = {-1, 1, 0, 0};
      static int[] dy = {0, 0, -1, 1};

      static int dfs(int x, int y) {
          if (x < 0 || x >= N || y < 0 || y >= M || board[x][y] == 'H') {
              return 0;
          }

          if (visited[x][y]) {
              System.out.println(-1);
              System.exit(0);
          }

          if (dp[x][y] != 0) {
              return dp[x][y];
          }

          visited[x][y] = true;
          int move = board[x][y] - '0';
          int max = 0;

          for (int dir = 0; dir < 4; dir++) {
              int nx = x + dx[dir] * move;
              int ny = y + dy[dir] * move;
              max = Math.max(max, dfs(nx, ny) + 1);
          }

          visited[x][y] = false;
          dp[x][y] = max;
          return dp[x][y];
      }

      public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          StringTokenizer st = new StringTokenizer(br.readLine());

          N = Integer.parseInt(st.nextToken());
          M = Integer.parseInt(st.nextToken());

          board = new char[N][M];
          dp = new int[N][M];
          visited = new boolean[N][M];

          for (int i = 0; i < N; i++) {
              String line = br.readLine();
              for (int j = 0; j < M; j++) {
                  board[i][j] = line.charAt(j);
              }
          }

          System.out.println(dfs(0, 0));
      }
  }
