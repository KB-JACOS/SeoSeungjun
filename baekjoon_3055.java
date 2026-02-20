import java.io.*;
import java.util.*;

public class baekjoon_3055 {

    static int[][] water;
    static char[][] map;
    static int R, C, sr, sc, ddr, ddc, result;
    static char DOT = '.', D = 'D', S = 'S';
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        water = new int[R][C];

        for(int i=0;i<R;i++){
            Arrays.fill(water[i], Integer.MAX_VALUE);
        }

        for(int i=0;i<R;i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<C;j++) {
                if(map[i][j] == 'D') {
                    ddr = i;
                    ddc = j;
                }
                
                if(map[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }
            }
        }

        flood();
        // print();
        boolean flag = proceed();
        System.out.println((flag)? result : "KAKTUS" );
        
        br.close();
    }

    public static boolean proceed() {
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{sr, sc, 0});
        
        boolean[][] visited = new boolean[R][C];
        while(!queue.isEmpty()){ 
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int w = cur[2];
            int nw = w + 1;

            for(int i=0;i<4;i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 'X' || visited[nr][nc]) {
                    continue;
                }

                int seq = water[nr][nc];

                if(seq <= nw) { continue; }
                queue.add(new int[] {nr, nc, nw});
                visited[nr][nc] = true;

                if(nr == ddr && nc == ddc) {
                    result = nw;
                    return true;
                }
            }
        }

        return false;
    }
    

    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void flood() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(map[i][j] == '*') {
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int w = cur[2];

            water[r][c] = w;
            
            for(int i=0;i<4;i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] != DOT || visited[nr][nc]) {
                    continue;
                }
                queue.add(new int[]{nr, nc, w + 1});
                visited[nr][nc] = true;
            }
        }
    }

    public static void print() {
        for(char[] arr : map){
            for(char c : arr) {
                System.out.printf("%c", c);
            }
            System.out.println();
        }

        System.out.println();
    }
}