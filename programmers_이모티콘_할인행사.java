
import java.util.*;

/**
    이모티콘의 모든 할인 조합을 찾아서 
    가입자가 가장 많은 조합을 찾고 
    가입자가 같으면 가장 많이 팔린 조합을 찾는다.
*/

class Solution {

    int maxSubscriber, maxProfit;
    HashSet<String> hashSet = new HashSet<>(); 
    
    public int[] solution(int[][] users, int[] emotions) {
        search(new int[emotions.length], emotions, users);
        
        return new int[]{maxSubscriber, maxProfit};
    }
    
    public void search(int[] discount, int[] emoticons, int[][] users) {
        for(int i=0;i<discount.length;i++) {
            discount[i] += 1;
            
            String comb = combToString(discount);
            if(hashSet.contains(comb) || discount[i] > 4) { 
                discount[i] -= 1;
                continue;
            }
            
            hashSet.add(comb);
            
            int[] prices = new int[discount.length];
            int[] rates = new int[discount.length];
            for(int j=0;j<discount.length;j++) {
                prices[j] = emoticons[j] - emoticons[j] * (discount[j] * 10) / 100;
                rates[j] = discount[j] * 10;
            } 
            
            calcBuying(rates, prices, emoticons, users);
            search(discount, emoticons, users);
            discount[i] -= 1;
        }
    }
    
    private void calcBuying(int[] rates, int[] prices, int[] emoticons, int[][] users) {
        int subscriber = 0; 
        int profit = 0;
        for(int i=0;i<users.length;i++) {
            int buyRate = users[i][0];
            int subscribeRate = users[i][1]; 
            
            int totalPrice = 0;
            
            for(int j=0;j<emoticons.length;j++) {
                if(rates[j] >= buyRate) { totalPrice += prices[j]; }
            }
            
            if(totalPrice >= subscribeRate) {
                subscriber+=1;
            }
            else {
                profit += totalPrice;
            }
        }
        
        if(subscriber > maxSubscriber) {
            maxSubscriber = subscriber;
            maxProfit = profit;
        }
        else if(subscriber == maxSubscriber) {
            maxProfit = Math.max(maxProfit, profit);
        }
    }
    

    public String combToString(int[] discount) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<discount.length;i++) {
            sb.append(discount[i]);
        }

        return sb.toString();
    }
}