package swea_커피점and제과점;

import java.util.*;

class UserSolution {

    final int INF = Integer.MAX_VALUE;
    int N;
    List<List<int[]>> graph = new ArrayList<>();

    public void init(int N, int K, int sBuilding[], int eBuilding[], int mDistance[]) {

        this.N = N;

        graph.clear();
        for(int i=0;i<N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<K;i++){
            add(sBuilding[i], eBuilding[i], mDistance[i]);
        }
    }

    public void add(int sBuilding, int eBuilding, int mDistance) {

        graph.get(sBuilding).add(new int[]{eBuilding, mDistance});
        graph.get(eBuilding).add(new int[]{sBuilding, mDistance});
    }

    public int calculate(int M, int mCoffee[], int P, int mBakery[], int R) {

        Set<Integer> cafeSet = new HashSet<>();
        Set<Integer> bakerySet = new HashSet<>();

        for(int i=0;i<M;i++){
            cafeSet.add(mCoffee[i]);
        }

        for(int i=0;i<P;i++){
            bakerySet.add(mBakery[i]);
        }

        int[] distCoffee = multiDijkstra(mCoffee, M);
        int[] distBakery = multiDijkstra(mBakery, P);

        int ans = INF;

        for(int i=0;i<N;i++){

            if(cafeSet.contains(i) || bakerySet.contains(i)) continue;

            if(distCoffee[i] <= R && distBakery[i] <= R){
                ans = Math.min(ans, distCoffee[i] + distBakery[i]);
            }
        }

        return ans == INF ? -1 : ans;
    }

    int[] multiDijkstra(int[] sources, int size){

        int[] dist = new int[N];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1],  o2[1]));

        for(int i=0;i<size;i++){

            int s = sources[i];

            if(s < 0) continue;

            dist[s] = 0;
            pq.add(new int[]{s,0});
        }

        while(!pq.isEmpty()){

            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];

            if(cost > dist[node]) continue;

            for(int[] next : graph.get(node)){

                int nextNode = next[0];
                int nextCost = cost + next[1];

                if(nextCost < dist[nextNode]){
                    dist[nextNode] = nextCost;
                    pq.add(new int[]{nextNode, nextCost});
                }
            }
        }

        return dist;
    }
}