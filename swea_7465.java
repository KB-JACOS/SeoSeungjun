import java.io.*;
import java.util.*;

public class swea_7465 {

    static int[] root;
    static int[] rank;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=T;tc++) { 

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            root = new int[N+1];
            rank = new int[N+1];

            for(int i=1;i<=N;i++) {
                root[i] = i;
            }

            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            int cnt = 0;
            boolean[] visited = new boolean[N + 1];
            for(int i=1;i<=N;i++) {
                int cur = findParent(i);
                // System.out.printf("i=%d cur=%d\n", i, cur);
                if(visited[cur]) continue;

                visited[cur] = true;
                cnt++;
            }
            
            sb.append("#" + tc + " " + cnt + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void union(int a, int b) {
    
        int rootA = findParent(a);
        int rootB = findParent(b);

        if(rootA == rootB) {
            return;
        }
        
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


    public static int findParent(int num) {

        if(root[num] != num) {
            return root[num] = findParent(root[num]);
        }

        return root[num];
    }
}
