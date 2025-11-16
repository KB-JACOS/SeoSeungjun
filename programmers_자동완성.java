import java.util.*;

class Solution {
    public int solution(String[] words) {
        Arrays.sort(words); 
        
        String before = "";
        int sum = 0;
        
        for(int i=0;i<words.length;i++) {
            String cur = words[i];
            String next = (i == words.length - 1) ? "" : words[i+1];
            
            String same1 = findSame(before, cur);
            String same2 = findSame(cur, next);
            
            int count = Math.max(same1.length(), same2.length());

            sum += count;
            if(cur.length() > count) {
                sum += 1;
            }
            
            before = cur;
        }
        
        return sum;
    }
    
    public String findSame(String a, String b) {
        
        char[] ac = a.toCharArray();
        char[] ab = b.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<Math.min(ac.length, ab.length);i++){
            if(ac[i] == ab[i]){
                sb.append(ac[i]);
            }
            else {
                break;
            }
        }
        
        
        return sb.toString();
    }
}