class programmers_보행자_천국 {
    int MOD = 20170805;
    int M, N;
    int[][] map;

    public int solution(int m, int n, int[][] cityMap) {

        map = cityMap;
        M = m;
        N = n;
        
        int[][] dp = new int[m][n]; 
        dp[0][0] = 1;
        
        for(int r=0;r<m;r++) {
            for(int c=0;c<n;c++) {
                if(r == 0 && c == 0) continue;
                int lr = r, lc = c -1, ur = r - 1, uc = c;
                
                //LEFT 
                int fromLeft = (isNotValid(lr,lc)) ? 0 : findLeft(lr, lc, dp);
                //RIGHT
                int fromUp = (isNotValid(ur, uc)) ? 0 : findUp(ur, uc, dp);
                
                dp[r][c] = (fromLeft + fromUp) % MOD;
            }
            System.out.println();
        }
        
        return dp[M-1][N-1];
    }
    
    public int findLeft(int r, int c, int[][] dp) {
        
        while(true) {
            if(c < 0 || map[r][c] == 1) {
                return 0;
            }
            
            else if(map[r][c] == 0) {
                return dp[r][c];
            }
            
            else if(map[r][c] == 2) {
                c -= 1;
            }
        }
        
    }
    
    public int findUp(int r, int c, int[][] dp) {
        while(true) {
            if(r < 0 || map[r][c] == 1) {
                return 0; 
            }
            
            else if(map[r][c] == 0) {
                return dp[r][c];
            }
            
            else if(map[r][c] == 2) {
                r -= 1;
            }
        }
        
    }
    
    public boolean isNotValid(int r, int c ){
        return (r < 0 || r >= M || c < 0 || c >= N);
    } 
}