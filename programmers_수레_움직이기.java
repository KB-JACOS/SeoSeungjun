import java.util.*; 

class programmers_수레_움직이기 {
    
    final int redStart = 1, blueStart = 2, redStop = 3, blueStop = 4;
    int[][] map, index = new int[5][2];
    
    int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    int min = Integer.MAX_VALUE; 
    int N, M;
    
    public int solution(int[][] maze) {
        N = maze.length; 
        M = maze[0].length;
        map = maze;
        
        init(maze, index);
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{index[redStart][0], index[redStart][1], index[blueStart][0], index[blueStart][1], 0});
        
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[index[redStart][0]][index[redStart][1]][index[blueStart][0]][index[blueStart][1]] = true;

        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int rr = cur[0], rc = cur[1], br = cur[2], bc = cur[3], depth = cur[4];
            
            //RED
            for(int i=0;i<4;i++) {
                int nrr = rr + dr[i];
                int nrc = rc + dc[i];
                
                boolean redFlag = false;
                if(rr == index[redStop][0] && rc == index[redStop][1]) {
                    nrr = rr; 
                    nrc = rc;
                    redFlag = true;
                }
                
                if(isValid(nrr, nrc) || maze[nrr][nrc] == 5) {
                    continue;
                }
                
                //BLUE
                for(int j=0;j<4;j++) {
                    int nbr = br + dr[j];
                    int nbc = bc + dc[j];
                    
                    boolean blueFlag = false;
                    if(br == index[blueStop][0] && bc == index[blueStop][1]) {
                        nbr = br; 
                        nbc = bc;
                        blueFlag = true;
                    }
                    
                    if(isValid(nbr, nbc) || maze[nbr][nbc] == 5) { continue; }
                    
                    if( blueFlag  && redFlag ) return depth;
                    if(visited[nrr][nrc][nbr][nbc]) { continue; }
                    
                    if (nrr == nbr && nrc == nbc) continue;
                    if (nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;

                    visited[nrr][nrc][nbr][nbc] = true;
                    queue.add(new int[] {nrr, nrc, nbr, nbc, depth+1});
                }
            }
        }
        
        return 0;
    }
    
    public boolean isValid(int r, int c){ 
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    public void init(int[][] maze, int[][] index) {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++) {
                if(maze[i][j] == redStart) {
                    index[redStart][0] = i; 
                    index[redStart][1] = j;
                        
                }
                if(maze[i][j] == blueStart) {
                    index[blueStart][0] = i;
                    index[blueStart][1] = j;
                }
                if(maze[i][j] == redStop) {
                    index[redStop][0] = i; 
                    index[redStop][1] = j;
                }
                if(maze[i][j] == blueStop) {
                    index[blueStop][0] = i;
                    index[blueStop][1] = j; 
                }
            }
        }
    }
}