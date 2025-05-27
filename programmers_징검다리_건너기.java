import java.util.*;

class programmers_징검다리_건너기 {
    int max = Integer.MIN_VALUE, maxIndex = 0;
    public int solution(int[] stones, int k) {
        int answer = 0;
        int N = stones.length;
        
        int[] arr = new int[N];
        for(int i=0;i<k;i++) {
            if(max < stones[i])  {
                max = stones[i];
                maxIndex = i;
            }
        }
        
        answer = max;
        arr[0] = max;
        //System.out.println(arr[0] + "maxIndex =" + maxIndex);
        
        for(int i=1;i<=N-k;i++) {
            if(maxIndex < i){ updateMaxIndex(stones, i, k); }
            
            else if(max < stones[i+k-1]){
                max = stones[i+k-1];
                maxIndex = i+k-1;
            }

            
            
            answer = Math.min(answer, max);
        }

        return answer;
    }
    
    public void updateMaxIndex(int[] stones, int cur, int k){
        //System.out.printf("udpate-");
        max = Integer.MIN_VALUE;
        for(int i=cur; i<cur+k;i++) {
            if(max < stones[i])  {
                max = stones[i];
                maxIndex = i;
            }
        }
    }
}