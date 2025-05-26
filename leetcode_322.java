class Solution {
    public int coinChange(int[] coins, int amount) {
        Deque<int[]> queue = new ArrayDeque<>(); 

        queue.offer(new int[] {amount, 0});

        boolean[] visited = new boolean[amount+1];
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int left = cur[0];
            int cnt = cur[1];

            if(left == 0) return cnt;

            for(int i=0;i<coins.length;i++){
                int next = left - coins[i];
                if(next < 0 || visited[next]){ continue; }
                visited[next] = true;
                queue.offer(new int[] {next, cnt+1});
            }
        }
        return -1;   
    }
}
