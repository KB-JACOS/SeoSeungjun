import java.util.*;
import java.io.*;

public class baekjoon_1774 {

    static int[] root, rank;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken());

        rank = new int[N+1];
        root = new int[N+1];

        PriorityQueue<God> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.w, o2.w));
        
        List<int[]> gods = new ArrayList<>();
        gods.add(new int[]{-1, -1});
        for(int i=1;i<=N;i++) {
            root[i] = i;
            st= new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gods.add(new int[]{x, y});
        }

        boolean[][] connected = new boolean[N+1][N+1];
        for(int i=0;i<M;i++ ) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            connected[s][e] = true;
            connected[e][s] = true;

            pq.add(new God(s, e, 0));
        }

        for(int i=1;i<=N;i++) {
            for(int j=i;j<=N;j++) {

                if(connected[i][j]) continue;

                int[] a = gods.get(i);
                int[] b = gods.get(j);
                
                double distance = Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
                pq.add(new God(i, j, distance));
            }
        }

        double cnt = 0;
        while(!pq.isEmpty()) {
            
            God cur = pq.poll();

            int rootA = find(cur.a);
            int rootB = find(cur.b);

            if(rootA != rootB)  {
                union(cur.a, cur.b);
                cnt += cur.w;
            }
        }

        System.out.printf("%.2f\n", cnt);

        
    }

    static class God {
        int a, b;
        double w; 

        public God(int a, int b, double w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    public static void union(int a, int b) {
        
        int rootA = root[a];
        int rootB = root[b];

        if(rootA == rootB) return;

        if(rank[rootA] < rank[rootB]) {
            root[rootA] = rootB;
        }
        else if(rank[rootA] > rank[rootB]) {
            root[rootB] = rootA;
        }
        else {
            root[rootB] = rootA;
            rank[rootA]++;
        }

    }

    public static int find(int num) {
        if(root[num] != num) {
            root[num] = find(root[num]);
        }

        return root[num];
    }
    
}