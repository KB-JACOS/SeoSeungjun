import java.io.*;
import java.util.*;

public class baekjoon_2169 {
    
    static long[][] memo = new long[31][31];
    static final int LEFT = 0, RIGHT = 1, DOWN = 2;
    static int N, M;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[N][M][4];
        int[][] map = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(br.readLine());
            }
        }

        dp[0][0][LEFT] = map[0][0];
        dp[0][0][RIGHT] = map[0][0];
        
        for(int i=1;i<M;i++) {
            dp[0][i][LEFT] = dp[0][i-1][LEFT] + map[0][i];
            dp[0][i][DOWN] = dp[0][i-1][DOWN] + map[0][i];
        }
        
        for(int i=1;i<N;i++) {
            rightDir(dp, map, i);
            leftDir(dp, map, i);
            downDir(dp, map, i);
        }

        System.out.println(dp[N - 1][M - 1][DOWN]);

    }

    private static void rightDir(int[][][] dp, int[][] map, int row) {
        
        //맨 왼쪽 첫칸은 바로 윗칸의 메모 값이랑 더하기
        dp[row][0][RIGHT] = map[row][0] + dp[row - 1][0][DOWN];

        for(int col=1;col<M;col++) {
            if(dp[row][col-1][RIGHT] >= dp[row - 1][col][DOWN]){
                dp[row][col][RIGHT] = dp[row][col-1][RIGHT] + map[row][col];
            }

            else {
                dp[row][col][RIGHT] = dp[row - 1][col][DOWN] + map[row][col];
            }
        }
    }


    private static void leftDir(int[][][] dp, int[][] map, int row) {

        //맨 오른쪽 첫칸은 바로 윗칸의 메모 값이랑 더하기
        dp[row][M - 1][LEFT] = map[row][M - 1] + dp[row - 1][M - 1][DOWN];

        //왼쪽으로 가면서 최대값 찾기
        for(int col=M - 2;col>=0;col--) {
            if(dp[row][col+1][LEFT] >= dp[row - 1][col][DOWN]){
                dp[row][col][LEFT] = dp[row][col+1][RIGHT] + map[row][col];
            }

            else {
                dp[row][col][LEFT] = dp[row - 1][col][DOWN] + map[row][col];
            }
        }
    }

    private static void downDir(int[][][] dp, int[][] map, int row) {

        //왼쪽 방향 오른쪽 방향 둘다 
        for(int col=0;col<M;col++) {
            dp[row][col][DOWN] = Math.max(dp[row][col][LEFT], dp[row][col][RIGHT]);
        }
    }
}