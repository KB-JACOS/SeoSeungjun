import java.util.*;
/** 
각 칸별로 신발의 사용 여부 메모 
Queue에 신발 사용여부도 추가 
	*/ 

class programmers_121690 {
    
    int[] dc = {0, 0, -1, 1, 0, 0, -2, 2}, dr = {-1, 1, 0, 0, -2, 2, 0, 0}; // 상하좌우
    boolean[][] holes;
    boolean[][][] visited;
    public int solution(int n, int m, int[][] hole) {
        Queue<int[]> queue = new ArrayDeque<>();    //y, x, cnt, isJump

        visited=new boolean[n+1][m+1][2];
        holes=new boolean[n+1][m+1];

        for(int[] cur : hole){
            holes[cur[0]][cur[1]] = true;
        }

        queue.add(new int[] {1, 1, 0, 0});
        
        while(!queue.isEmpty()){
            int[] cur = queue.remove();
            int r = cur[0], c = cur[1], cnt = cur[2], isJump = cur[3];
            
            for(int i=0;i<((isJump==1) ? 4 : 8);i++){
                int nr = r + dr[i], nc = c + dc[i], nj = (isJump==1) ? 1 : (i/4);
                
                if(nr>0 && nc>0 && nr<=n && nc<=m ){
                    if( !visited[nr][nc][nj] && !holes[nr][nc] ){
                        visited[nr][nc][nj] = true;
                        if(nr == n && nc == m) return cnt+1;
                        queue.add(new int[]{nr, nc, cnt+1, nj});
                    }    
                }
            }           
        }
        
        return -1;
    }
}