import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        List<Player> arr = new ArrayList<>();
        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int color = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arr.add(new Player(i, color, cost));
        }

        Collections.sort(arr, (o1, o2) -> {
            if(o1.cost == o2.cost) {
                return Integer.compare(o1.color, o2.color);
            }

            return Integer.compare(o1.cost, o2.cost);
        });

        int[] dp = new int[200001];

        int ballIdx = 0, sum = 0;
        for(int i=1;i<=N;i++) {

            Player cur = arr.get(i - 1);
        
            while(arr.get(ballIdx).cost < cur.cost) {
                Player small = arr.get(ballIdx);
                sum += small.cost;
                dp[small.color] += small.cost;
                ballIdx++;
            }
            
            cur.result = sum - dp[cur.color];
        }

        Collections.sort(arr, (o1, o2) -> Integer.compare(o1.idx, o2.idx));

        for(Player p : arr) {
            System.out.println(p.result);
        }
    }
    
    public static class Player{
        int color;
        int cost;
        int idx;
        int result = 0;

        public Player(int idx, int color, int cost) {
            this.color = color;
            this.cost = cost;
            this.idx = idx;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
}

