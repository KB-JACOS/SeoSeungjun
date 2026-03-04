import java.util.*;
import java.io.*;

public class baekjoon_1753 {

    static int V;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<List<int[]>> graph = new ArrayList<>();

        for(int i=0;i<=V;i++) {
            graph.add(new ArrayList<>());
        }

        int S = Integer.parseInt(br.readLine());

        for(int i=1;i<=E;i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(from).add(new int[] {to, w});
            // graph.add(new )
        }

        int[] result = dajikstra(S, V, graph);
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=V;i++) {
            if(result[i] == Integer.MAX_VALUE) {
                sb.append("INF"+ "\n");
            }
            
            else {
                sb.append(result[i] + "\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static int[] dajikstra(int start, int end, List<List<int[]>> graph) {

        int[] dist = new int[V+1];

        for(int i=0;i<dist.length;i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
    
        pq.add(new int[] {start, 0});
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int w = cur[1];

            if(dist[now] < w) continue;

            for(int[] e : graph.get(now)) {
                int nextTo = e[0];
                int nextW = e[1] + w;

                if(nextW < dist[nextTo]) {
                    dist[nextTo] = nextW;
                    pq.add(new int[] {nextTo, nextW});
                }
            }
        }

        return dist;
    }

}