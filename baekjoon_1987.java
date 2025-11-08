    import java.util.*;
    import java.io.*;

    public class Main {

        static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1}; // 상 하 좌 우
        static int R, C, result = 0;
        static char[][] map;

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());   

            map = new char[R][C];

            for(int i=0;i<R;i++) {
                String line = br.readLine();
                for(int j=0;j<C;j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            boolean[] visited = new boolean[26];
            boolean[][] visitedMap = new boolean[R][C];
            visitedMap[0][0] = true;
            visited[map[0][0] - 'A'] = true;
            find(0, 0, visited, 1, visitedMap);

            System.out.println(result);
        }

        public static void find(int r, int c, boolean[] visited, int cnt, boolean[][] visitedMap) {

            for(int i=0;i<4;i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr < 0 || nr >= R || nc < 0 || nc >= C || visitedMap[nr][nc]) {
                    result = Math.max(result, cnt);
                    continue;
                }

                int nextAlphaIdx = map[nr][nc] - 'A';

                if(visited[nextAlphaIdx]) {
                    result = Math.max(result, cnt);
                    continue;
                }

                visited[nextAlphaIdx] = true;
                visitedMap[nr][nc] = true;
                find(nr, nc, visited, cnt+1, visitedMap);
                visitedMap[nr][nc] = false;
                visited[nextAlphaIdx] = false;
            }
        }



}
