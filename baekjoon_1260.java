import java.util.*;
import java.io.*;


public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean[] visited;
    static List<List<Integer>> edge;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int init = Integer.parseInt(st.nextToken());
        
        visited = new boolean[N+1];
        edge = new ArrayList<>();

        for(int i=0;i<=N;i++){
            edge.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            edge.get(v1).add(v2);
            edge.get(v2).add(v1);
        }
        
        for(List<Integer> list : edge){
            Collections.sort(list);
        }

        dfs(init);
        visited = new boolean[N+1];
        bw.write("\n");
        bfs(init);
        
        bw.close();
    }

    public static void bfs(Integer init) throws IOException{
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(init);
        
        while(!queue.isEmpty()){
            Integer v = queue.poll();
            visited[v] = true;
            bw.write(String.format("%d ", v));
            
            for(Integer i : edge.get(v)){
                if(visited[i] == false){
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void dfs(Integer init) throws IOException {
        Stack<Integer> st = new Stack<>();
        st.push(init);
    
        while (!st.isEmpty()) {
            int v = st.pop();
            if (!visited[v]) {
                visited[v] = true;
                bw.write(String.format("%d ", v));
                
                List<Integer> thisEdge = edge.get(v);
                for (int i = thisEdge.size() - 1; i >= 0; i--) {
                    int next = thisEdge.get(i);
                    if (!visited[next]) {
                        st.push(next);
                    }
                }
            }
        }
    }
    
}