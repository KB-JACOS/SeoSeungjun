import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class baekjoon_11559 {

    static char map[][] = new char[12][6];

    static char dot = '.', pop = 'p'; 
    public static void main(String[] args) throws Exception{
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<12;i++) {
            map[i] = br.readLine().toCharArray();
        }

        int result = 0;
        while(true) {
            int cnt = pop();

            if(cnt>0) result++;

            else if(cnt == 0) break;
        }
        System.out.println(result);
    }


    public static int pop() {
        int cnt = 0;
        for(int i=0;i<12;i++) {
            for(int j=0;j<6;j++) {

                if(map[i][j] == dot || map[i][j] == pop) continue;
                List<int[]> popList =  isPopable(i, j);
                if(popList.size() >= 4) {
                    cnt++;
                    for(int[] p : popList) {
                        int r = p[0];
                        int c = p[1];
                        
                        map[r][c] = pop;
                    }
                }
            }
        }
        gravity();
        return cnt;
    }

    public static void gravity() {
        for(int i=0;i<6;i++) {
            Queue<Character> queue = new LinkedList<>();
            
            for(int j=map.length - 1; j>=0;j--) {
                if(map[j][i] == pop || map[j][i] == dot) {
                    continue;
                }
                queue.add(map[j][i]);
            }

            for(int j=map.length - 1; j>=0;j--) {
                if(queue.isEmpty()) {
                    map[j][i] = dot;
                    continue;
                }
                
                char color = queue.poll();
                map[j][i] = color;
            }
        }
    }

    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static List<int[]> isPopable(int r, int c) {
        char color = map[r][c];
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> result = new ArrayList<>();
    
        queue.add(new int[]{r, c});
        result.add(new int[]{r, c});

        boolean[][] visited = new boolean[12][6];
        visited[r][c] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
    
            for(int i=0;i<4;i++) {
                int nr = cur[0] + dr[i]; 
                int nc = cur[1] + dc[i];
                
                if(nr < 0 || nc < 0 || nr >= 12 || nc >= 6 || visited[nr][nc]) {
                    continue;
                }

                if(map[nr][nc] == color) {
                    visited[nr][nc] = true;
                   result.add(new int[]{nr, nc});
                   queue.add(new int[] {nr, nc});
                }
            }
        }
        
        return result;
    }
}