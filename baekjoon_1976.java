import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] used;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=1;j<=N;j++) {
                int conn = Integer.parseInt(st.nextToken());

                if(conn == 1) {
                    graph.get(j).add(i);
                    graph.get(i).add(j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dest = new int[M];
        for(int i=0;i<M;i++) {
            dest[i] = Integer.parseInt(st.nextToken());
            
        }

        boolean result = false;

        for(int i=1;i<M;i++) {

            Queue<Integer> queue = new LinkedList<>();

            int start = dest[i-1];
            int next = dest[i];
            
            boolean find = false;
            boolean[] visited = new boolean[N + 1];

            if(start == next) {
                find = true;
            }

            else {
                queue.add(start);
            }

            while(!queue.isEmpty()) {

                int cur = queue.poll();

                for(int neighbor : graph.get(cur)) {
                    if(visited[neighbor]) {
                        continue;
                    }
                    
                    if(neighbor == next) {
                        find = true;
                        break;
                    }
                    
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }

            if(!find) { 
                result = false; 
                break; 
            }
            else { result = true; }
        }

        System.out.println((result) ? "YES" : "NO");
    }
}
