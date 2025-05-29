class Solution {
    
    boolean[] visited;
    int cnt = 0;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];

        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                visited[i] = true;
                recur(computers, i);
                cnt++;
            }
        }
        return cnt;
    }
    
    public void recur(int[][] computers, int vertex) {
        int n = computers.length;
        for(int i=0;i<n;i++){
            if(computers[vertex][i] == 1 && !visited[i]){
                visited[i] = true;
                recur(computers, i);
            }
        }
    }
}