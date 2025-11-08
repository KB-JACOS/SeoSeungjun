    import java.util.*;
    import java.io.*;

    public class baekjoon_10986 {
        
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            long[] sum = new long[N+1];
            long[] dp = new long[M];

            st = new StringTokenizer(br.readLine());
            
            long cnt = 0;
            for(int i=1;i<=N;i++) {
                int num =Integer.parseInt(st.nextToken());

                sum[i]= (sum[i-1] + num) % M;

                if(sum[i] % M == 0) cnt++;
                dp[(int) sum[i]] += 1;
            }
            
            for(int i=0;i<M;i++) {
                cnt += (dp[i] * (dp[i] - 1)) / 2;
            }

        
        System.out.println(cnt);
    }
}
