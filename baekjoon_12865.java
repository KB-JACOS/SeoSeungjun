import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, K; 
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] stuff = new int[N+1][2];
        int[][] dp = new int[N+1][K+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            stuff[i][0] = Integer.parseInt(st.nextToken());
            stuff[i][1] = Integer.parseInt(st.nextToken());
        }



        for(int i=1;i<=N;i++) {
            int W = stuff[i][0];
            int V = stuff[i][1];

            for(int j=0;j<=K;j++){
                if( j < W) {
                    dp[i][j] = dp[i-1][j];
                }
                
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - W] + V);
                }
            }
        }

        System.out.println(dp[N][K]);
    }

}
