import java.util.*;
import java.io.*;

public class baekjoon_11404 {

    static final int INF = 1000000000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        int[][] dist = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for(int i=0;i<E;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            dist[s][e] = Math.min(dist[s][e], w);
        }

        for(int k=1;k<=N;k++){
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){

                    if(dist[i][k] == INF || dist[k][j] == INF) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(dist[i][j] == INF) bw.write("0 ");
                else bw.write(dist[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
    }
}