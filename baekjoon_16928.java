import java.util.*;
import java.io.*;

public class Main {
    static int[] ladder = new int[101], snake = new int[101];
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine()) ;

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int up = Integer.parseInt(st.nextToken());
            int down = Integer.parseInt(st.nextToken());
            ladder[up] = down;
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int up = Integer.parseInt(st.nextToken());
            int down = Integer.parseInt(st.nextToken());    
            snake[up] = down;
        }

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {1, 0});

        int result = 0;
        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int cur = arr[0], cnt = arr[1];

            if(cur == 100) {
                result = cnt;
                break;
            }

            for(int i=1;i<=6;i++) {
                int next;

                if(cur + i > 100) continue;
                
                if(ladder[cur+i] != 0) next = ladder[cur+i]; 
                else if(snake[cur+i] != 0) next = snake[cur+i];
                else next = cur + i;
                
                if(next <= 100 && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] { next, cnt+1 });
                }
            }
        }

        System.out.println(result);
    }
}