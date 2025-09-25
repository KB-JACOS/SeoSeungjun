import java.io.*;

public class baekjoon_2193 {

    static Long[][] dp; //[level][root]
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer N = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.println(1);
        }

        else{
            dp = new Long[N+1][2];

            dp[1][1] = 1L;
            dp[2][0] = 2L;
            dp[2][1] = 1L;

            System.out.println(recur(N, 1));
        }
    }

    public static Long recur(int level, int root){

        if(dp[level][root] != null){
            return dp[level][root];
        }

        long result = 0;
        if(root == 1){
            result = recur(level-1, 0);
        }

        else {
            result = recur(level-1, 0) + recur(level-1, 1); 
            
        }

        dp[level][root] = result;
        return result;
    }
}
