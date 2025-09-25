import java.util.*; 

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (o1, o2) ->  Integer.compare(o1[0], o2[0]) );
        int cnt = 1;
        int cur = routes[0][1];
        
        for(int[] route : routes) {
            int start = route[0];
            int end = route[1];
            
            
            if(start <= cur) {
                if( end < cur ) cur = end;
                continue;
            }
            
            else {
                cnt ++;
                cur = end;
            }
            
            
        }
        
        return cnt;
    }
}