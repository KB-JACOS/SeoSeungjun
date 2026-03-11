import java.util.*; 
import java.io.*;

public class baekjoon_14938 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] item = new int[N+1];
        st=new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }
        List<List<Integer>> graph = new ArrayList<>();
        int[][] weight = new int[N+1][N+1];

        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
            weight[a][b] = w;
            weight[b][a] = w;
        }

        int max = Integer.MIN_VALUE;
        for(int i=1;i<=N;i++) {
            int[] dist = new int[N+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            
            dajikstra(dist, i, graph, weight);

            int cnt = 0;
            for(int j=1;j<=N;j++) {
                if(dist[j] <= M) {
                    cnt += item[j];
                }
            }

            max = Math.max(cnt, max);
        }

        System.out.println(max);
        
    }
    

    public static void dajikstra(int[] dist, int start, List<List<Integer>> graph, int[][] weight) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

        pq.add(new int[]{start, 0});
        dist[start] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            int a = cur[0];
            int w = cur[1];

            for(int b : graph.get(a)) {
                
                int nextW = weight[a][b] + w;
                if(nextW > dist[b]) {
                    continue;
                }

                dist[b] = nextW;
                pq.add(new int[] {b, nextW});
            }
        }
    }
}