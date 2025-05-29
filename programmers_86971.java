import java.util.*;

class programmers_86971 {
    
    boolean[] visited; 
    List<List<Integer>> tree;
    int N;
    int answer;
    
    public int solution(int n, int[][] wires) {
        N = n;
        answer = n;
        visited = new boolean[n+1];
        tree = new ArrayList<>(n+1);
        
        for(int i=0;i<=n;i++) { tree.add(new ArrayList<>()); }
        for(int[] w : wires) { 
            tree.get(w[0]).add(w[1]); 
            tree.get(w[1]).add(w[0]);
        }
        
        search(1);
        
        
        return answer;
    }
    
    public int search(int v) {
        visited[v] = true;
        int cnt = 1;
        for(int vertex : tree.get(v)){
            if(!visited[vertex]){
                cnt += search(vertex);
            }
        }
        
        answer = Math.min(answer, Math.abs( N - 2 * cnt));
        //System.out.printf("v=%d cnt=%d answer=%d \n", v, cnt, answer);
        return cnt;
    }
}