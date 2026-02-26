import java.util.*;
import java.io.*;

public class baekjoon_1197 {
    
    static int[] parent, rank;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        parent = new int[V+1]; 
        rank = new int[V+1];
        visited = new boolean[V+1];

        for(int i=1;i<=V;i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        
        for(int i=1;i<=E;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new int[] {s, e, w});
        }

        long total = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            int s = cur[0];
            int e = cur[1];
            int w = cur[2];
            
            int rootS = find(s);
            int rootE = find(e);

            if(rootS != rootE) {
                union(s, e);
                cnt++;
                total += w;
            }

            if(cnt == V - 1) break;
        }

        System.out.println(total);
    }

    public static void union(int a, int b)  {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;

        if(rank[rootA] == rank[rootB]) {
            parent[rootB] = rootA;
            rank[rootA] ++;
        }

        else if(rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        }

        else if(rank[rootB] > rank[rootA]) {
            parent[rootA] = rootB;
        }

    }

    public static int find(int v) {
        
        if(parent[v] != v) {
            parent[v] = find(parent[v]); 
        }
        
        return parent[v];
    }

}