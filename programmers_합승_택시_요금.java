import java.util.*;

class programmers_합승_택시_요금 {
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Edge>> graph = new ArrayList<>(n);
        for(int i=0;i<=n;i++) { graph.add(new ArrayList<>()); }
        
        // 무방향 그래프 
        for(int i=0;i<fares.length;i++) {
            int src = fares[i][0];
            int dst = fares[i][1];
            int cost = fares[i][2];
            
            graph.get(src).add(new Edge(dst, cost));
            graph.get(dst).add(new Edge(src, cost));
        }
                              
        int[][] dist = new int[3][n+1];
        for(int i=0;i<3;i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        
        
        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int[] start = {s, a, b};
        
        for(int i=0;i<3;i++) {
            int[] thisDist = dist[i];
            pq.add(new Edge(start[i], 0));
            thisDist[start[i]] = 0;
            
            while(!pq.isEmpty()){
                Edge cur = pq.poll();
                
                if (thisDist[cur.node] < cur.cost) continue;

                for(Edge edge : graph.get(cur.node)) {                    
                    int newDist = edge.cost + thisDist[cur.node];
                    if(newDist < thisDist[edge.node] ) {
                        thisDist[edge.node] = newDist;
                        pq.add(new Edge(edge.node, newDist));
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;

        for (int k = 1; k <= n; k++) {
            int totalCost = dist[0][k] + dist[1][k] + dist[2][k];
            answer = Math.min(answer, totalCost);
        }
        
        return answer;
    }
    
    class Edge {
        int node;
        int cost;
        
        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        
    }
}