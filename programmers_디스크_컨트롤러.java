import java.util.*;

class Solution {
    public int solution(int[][] jobs) {

        Queue<Disk> pq = new PriorityQueue<Disk>((o1, o2) -> o1.time - o2.time);

        int curTime = 0; 
        int sum = 0;
        int idx = 0;
        int count = 0;
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        while(count < jobs.length) {
            
            while( idx < jobs.length && jobs[idx][0] <= curTime ) {
                pq.add(new Disk(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if( pq.isEmpty() ) {
                curTime = jobs[idx][0];
            }
            
            else {
                Disk disk = pq.poll();
                curTime += disk.time;
                sum += curTime - disk.start;
                count++;
            }
        }
        
        return sum / jobs.length;
        
    }
    
    public class Disk {
        int start, time;
        
        public Disk(int start, int time) {
            this.start = start; 
            this.time = time; 
        }
    }
}