import java.util.*;
import java.io.*;

public class baekjoon_6497 {

    static int[] root, rank;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // System.out.println(N + " " + M);
            if(N == 0 && M == 0) {
                break;
            }

            root = new int[N+1];
            rank = new int[N+1];

            for(int i=1;i<=N;i++) {
                root[i] = i;
            }

            int sum = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
            for(int i=0;i<M;i++){
                
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                pq.add(new int[] {s, e, w});
                sum+=w;
            }

            
            
            int cnt =0;
            while(!pq.isEmpty()){
                int[] cur = pq.poll();
                
                int s = cur[0];
                int e = cur[1];
                int w = cur[2];

                int rootS = find(s);
                int rootE = find(e);

                if(rootS == rootE) {
                    continue;
                }
                else {
                    union(s, e);
                    cnt+= w;
                }
            }
            bw.write(sum - cnt + "\n");
            // System.out.println(sum - cnt);
        }
        bw.flush();
        bw.close();
    }

    public static void union(int a, int b){
        
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;

        if(rank[rootA] < rank[rootB]) {
            root[rootA] = rootB;
        }
        else if(rank[rootA] > rank[rootB]) {
            root[rootB] = rootA;
        }
        else {
            root[rootB] = rootA;
            rank[rootB] += 1;
        }
    }

    public static int find(int num) {
        if(root[num] != num) {
            root[num] = find(root[num]);
        }

        return root[num];
    }
}
