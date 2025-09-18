import java.util.*;

public class programmers_경주로_건설 {
    
    final int ROW = 0;
    final int COL = 1;
    final int START = 3;
    
    public int solution(int[][] board) {
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        
        int size = board.length;
        int[][][] dp = new int[size][size][2];
        
        Deque<int[]> queue = new ArrayDeque<>();
        int result = Integer.MAX_VALUE;
        
        queue.add(new int[] {0, 0, 0, START});
        
        while(!queue.isEmpty()) {
            
            int[] cur = queue.poll(); 
            int r = cur[0], c = cur[1], cost = cur[2], direction = cur[3];
            
            if(r == size-1 && c == size-1) {
                result = Math.min(result, cost);
            }
            
            for(int i=0;i<4;i++){
                int nr = r + dr[i], nc = c + dc[i];

                if(nr >= size || nc >= size || nr < 0 || nc < 0 || board[nr][nc]== 1) {
                    continue;
                }
                
                int nextDirection = (r==nr) ? COL : ROW;
                int nextCost = cost + 100;
                
                if(direction == START) {
                    queue.add(new int[] {nr, nc, nextCost, nextDirection});
                    continue;
                }

                nextCost += (direction == nextDirection) ? 0 : 500;
                int[] next = {nr, nc, nextCost, nextDirection};
                
                if(dp[nr][nc][nextDirection] < nextCost && dp[nr][nc][nextDirection] != 0) {
                    continue;
                }
                
                dp[nr][nc][nextDirection] = nextCost;
                queue.add(next);
            }
        }
        
        return result;
    }
}
