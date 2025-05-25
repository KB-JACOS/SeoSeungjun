import java.util.*;

class programmers_176054 {
    
    boolean[][] graph; 
    int n, max = Integer.MIN_VALUE;
    boolean[] visited;
    Queue<int[]> queue = new LinkedList<>();
    
    public int solution(int n, int[][] edge) {
        
        int[] distancesArr = new int[n+1];
        graph = new boolean[n+1][n+1];
        visited = new boolean[n+1];
        this.n = n;
        
        // 그래프 만들기
        for(int i=0;i<edge.length;i++) {
            int r = edge[i][0], c = edge[i][1];
            
            graph[r][c] = true;   
            graph[c][r] = true;
        }
        
        //초기 queue 삽입
        visited[1] = true;
        queue.add(new int[] {1, 0});
        
        //bfs 탐색
        while(!queue.isEmpty()) {
            
            int[] info = queue.poll();
            int vertex = info[0], distance = info[1];
            
            distancesArr[vertex] = distance;
            
            max = Math.max( max, distance );
            for(int i=1;i<=n;i++) {
                if(graph[vertex][i] && !visited[i]){
                    visited[i] = true;
                    queue.add(new int[] {i, distance+1});
                }
            }
        }
        
        int cnt = 0;
        for(int i=1;i<=n;i++) {
            if(max == distancesArr[i]){
                cnt ++;
            }
        }
        
        return cnt;
    }

}