class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        boolean[][] isPuddle = new boolean[n+1][m+1];
        int[][] dp = new int[n+1][m+1]; 
        
        dp[1][1] = 1;
        
        for(int[] puddle : puddles) {
            int c = puddle[0];
            int r = puddle[1];
            
            isPuddle[r][c] = true;
        }
        
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if( i == 1 && j == 1 ) continue;
                if(isPuddle[i][j]){ continue; }
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
            }
        }
        
        // for(int i=1;i<=n;i++) {
        //     for(int j=1;j<=m;j++) {
        //         System.out.printf("%d ", dp[i][j]);
        //     }
        //     System.out.println();
        // }
        
        return dp[n][m];
    }

}