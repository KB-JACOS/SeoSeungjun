import java.util.*;

class Solution {

    int[][] dp;
    List<Integer>[] graph;
    int[] sales;

    public int solution(int[] sales, int[][] links) {
        int n = sales.length;
        this.sales = sales;

        dp = new int[n][2];
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] link : links) {
            int parent = link[0] - 1;
            int child = link[1] - 1;
            graph[parent].add(child);
        }

        dfs(0);

        return Math.min(dp[0][0], dp[0][1]);
    }

    void dfs(int cur) {

        // 현재 노드의 기본 DP 설정
        dp[cur][0] = 0;               // cur 불참
        dp[cur][1] = sales[cur];      // cur 참석 비용

        // 자식이 없다면 leaf
        if (graph[cur].isEmpty())
            return;

        int extra = Integer.MAX_VALUE;   // 강제로 참석시킬 비용 후보

        for (int child : graph[cur]) {
            dfs(child);

            // child가 불참하는 것이 더 싸면
            if (dp[child][0] < dp[child][1]) {

                dp[cur][0] += dp[child][0];   // 부모 불참: child도 불참(임시)
                dp[cur][1] += dp[child][0];   // 부모 참석: child는 둘 중 작은 값(dp[child][0])

                // 나중에 child를 참석시키기 위한 후보 비용 저장
                extra = Math.min(extra, dp[child][1] - dp[child][0]);
            }
            // child가 참석이 더 싸면 (or 같다면 참석 쪽을 택함)
            else {

                dp[cur][0] += dp[child][1];   // 부모 불참: child는 반드시 참석
                dp[cur][1] += dp[child][1];   // 부모 참석: child는 참석(dp[child][1])

                // 이미 하나 참석했으므로 extra = 0으로 고정
                extra = 0;
            }
        }

        // 부모(cur)가 불참할 경우, 반드시 자식 중 최소 1명은 참석해야 한다.
        dp[cur][0] += extra;
    }
}
