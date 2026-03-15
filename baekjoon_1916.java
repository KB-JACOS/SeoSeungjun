import java.util.*;
import java.io.*;

public class baekjoon_1916 { 
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<int[]>> graph = new ArrayList<>();
        int[] dist = new int[N+1];
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        // int cnt = 0;

        for(int i=1;i<=M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new int[] {e, w});

        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        pq.add(new int[] {start, 0});
        dist[start] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            int s = cur[0]; 
            int sum = cur[1];

            if(sum > dist[s]) continue;

            for(int[] next : graph.get(s)) {
                int d = next[0];
                int w = next[1];
                if(w + sum < dist[d]) {
                    pq.add(new int[] {d, w + sum});
                    dist[d] = w + sum;
                }
            }
        }
        System.out.println(dist[end]);
    }
}