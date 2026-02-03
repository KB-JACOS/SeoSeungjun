import java.io.*;
import java.util.*;

public class baekjoon_1234 {
    static int N, R, G, B;
    static long[][][][] dp = new long[11][101][101][101];
    static long[] fact = new long[11];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        fact[0] = 1;
        for (int i = 1; i <= 10; i++) fact[i] = fact[i - 1] * i;

        dp[0][0][0][0] = 1;

        for (int level = 1; level <= N; level++) {
            for (int r = 0; r <= R; r++) {
                for (int g = 0; g <= G; g++) {
                    for (int b = 0; b <= B; b++) {
                        long prev = dp[level - 1][r][g][b];
                        if (prev == 0) continue;

                        // 한 색
                        if (r + level <= R)
                            dp[level][r + level][g][b] += prev;
                        if (g + level <= G)
                            dp[level][r][g + level][b] += prev;
                        if (b + level <= B)
                            dp[level][r][g][b + level] += prev;

                        // 두 색
                        if (level % 2 == 0) {
                            int x = level / 2;
                            long comb = fact[level] / (fact[x] * fact[x]);

                            if (r + x <= R && g + x <= G)
                                dp[level][r + x][g + x][b] += prev * comb;
                            if (r + x <= R && b + x <= B)
                                dp[level][r + x][g][b + x] += prev * comb;
                            if (g + x <= G && b + x <= B)
                                dp[level][r][g + x][b + x] += prev * comb;
                        }

                        // 세 색
                        if (level % 3 == 0) {
                            int x = level / 3;
                            long comb = fact[level] / (fact[x] * fact[x] * fact[x]);

                            if (r + x <= R && g + x <= G && b + x <= B)
                                dp[level][r + x][g + x][b + x] += prev * comb;
                        }
                    }
                }
            }
        }

        long ans = 0;
        for (int r = 0; r <= R; r++)
            for (int g = 0; g <= G; g++)
                for (int b = 0; b <= B; b++)
                    ans += dp[N][r][g][b];

        System.out.println(ans);
    }
}
