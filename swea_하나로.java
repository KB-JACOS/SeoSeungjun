import java.util.*; 
import java.io.*;

public class swea_하나로 {

    static int[] root, rank;

    public static void main(String[] args) throws Exception { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=T;tc++) {
            List<Edge> edgeList = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());
            int[] X = new int[N+1], Y = new int[N+1];

            root = new int[N+1];
            rank = new int[N+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++) {
                root[i] = i;
                X[i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer( br.readLine());
            for(int i=1;i<=N;i++) {
                Y[i] = Integer.parseInt(st.nextToken());
            }
           double E = Double.parseDouble(br.readLine());

            for(int i=1;i<=N;i++) {
                for(int j=i+1;j<=N;j++){
                    int s = i;
                    int e = j;
                    long dx = X[i] - X[j];
                    long dy = Y[i] - Y[j];
                    double c = E * (dx*dx + dy*dy);
                    Edge edge = new Edge(s, e, c);
                    edgeList.add(edge);
                }
            }

            Collections.sort(edgeList, (o1, o2) -> Double.compare(o1.c, o2.c));

            double cnt = 0;
            int edgeCnt = 0;
            for(int i=0;i<edgeList.size();i++) {
                Edge cur = edgeList.get(i);

                int s = cur.s;
                int e = cur.e;
                double c = cur.c;

                if(find(s) != find(e)) {
                    union(s, e);
                    cnt+= c;
                    edgeCnt++;

                    if(edgeCnt == N -1 ) break;
                }
            }

            sb.append(String.format("#%d %.0f\n", tc, cnt));
        }
        System.out.println(sb.toString());
    }

    public static void union(int a, int b){ 
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA == rootB) return;

        if(rank[rootA] > rank[rootB]) {
            root[rootB] = rootA;
        }
        else if(rank[rootB] > rank[rootA]) {
            root[rootA] = rootB;
        }
        else {
            root[rootB] = rootA;
            rank[rootA]++;
        }
    }

    public static int find(int node) {
        if(root[node] != node) {
            root[node] = find(root[node]);
        }

        return root[node];
    }


    
    static class Edge {
        int s, e;
        double c;

        public Edge(int s, int e, double c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }
    }
}
