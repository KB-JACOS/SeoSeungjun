import java.util.*;

class Solution {
    
    int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0 ,0}; // 상하좌우
    
    public int solution(String[] maps) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        int N = maps.length;
        int M = maps[0].length();
        
        boolean[][] lVisted = new boolean[N][M], eVisited = new boolean[N][M];
        int result = -1, lx=0, ly=0;

        boolean isFoundLabor = false;
        int sy = 0, sx =0;
            
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                if(maps[i].charAt(j) == 'S'){
                    sy = i;
                    sx = j;
                    break;
                }
            }
        }
        queue.add(new int[] { sy, sx, 0 });
        
        //레버 찾기 
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0], x = cur[1], cnt = cur[2];
            
            if(maps[y].charAt(x) == 'L'){
                ly = y;
                lx = x;
                result = cnt;
                isFoundLabor=true;
                
            }
            
            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny<0 || nx<0 || ny>N-1 || nx>M-1 || lVisted[ny][nx] || maps[ny].charAt(nx)=='X') {
                    continue;
                }
                
                lVisted[ny][nx] = true;
                queue.add(new int[] { ny, nx, cnt+1 });
            }
        }
        
        //System.out.println(isFoundLabor);
        if( !isFoundLabor ) {return  -1;}
        
        queue.add(new int[] {ly, lx, result});
        //System.out.printf("ly=%d lx=%d result=%d\n", ly, lx, result);
        
        //출구찾기 찾기 
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0], x = cur[1], cnt = cur[2];
            
            //System.out.printf("proceed y=%d x=%d cnt=%d\n",y,x, cnt);
            if(maps[y].charAt(x) == 'E'){
                //System.out.printf("y=%d x=%d cnt=%d\n",y,x, cnt);
                return cnt;
            }
            
            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny<0 || nx<0 || ny>N-1 || nx>M-1 || eVisited[ny][nx] || maps[ny].charAt(nx) == 'X') {
                    continue;
                }
                
                eVisited[ny][nx] = true;
                queue.add(new int[] { ny, nx, cnt+1 });
            }
        }
        return -1;
    }
}