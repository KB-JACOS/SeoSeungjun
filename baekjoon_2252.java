import java.util.*;
import java.io.*;

public class baekjoon_2252 {

    public static void main(String[] args) throws Exception { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] w = new int[N + 1];

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=1;i<=M;i++) {
            st= new StringTokenizer(br.readLine());

            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());
            w[back]++;
            graph.get(front).add(back);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            if(w[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur + " ");
            for(Integer n : graph.get(cur)) {
                w[n]--;

                if(w[n] == 0) {
                    queue.add(n);
                }
            }
        }
        sb.append("\n");
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}