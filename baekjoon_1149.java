import java.util.*;
import java.io.*;

public class baekjoon_1149 {

    final static int R = 0, G = 1, B = 2;
    static int[][] dp;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][3];
        dp = new int[N][3];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][R] = arr[0][R];
        dp[0][G] = arr[0][G];
        dp[0][B] = arr[0][B];

        for (int i = 1; i < N; i++) {
            dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + arr[i][R];
            dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + arr[i][G];
            dp[i][B] = Math.min(dp[i-1][R], dp[i-1][G]) + arr[i][B];
        }

        int result = Math.min(Math.min(dp[N-1][R], dp[N-1][G]), dp[N-1][B]);
        System.out.println(result);

        
    }
}
