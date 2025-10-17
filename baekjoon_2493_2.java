import java.util.*;
import java.io.*;

public class baekjoon_2493_2 {
    
    public static void main(String[] args) throws Exception {

        Stack<Tower> senderStack = new Stack<>(); 
        Stack<Tower> receiverStack = new Stack<>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] answer = new int[N];

        for(int i=0;i<N;i++) {
            int height = Integer.parseInt(st.nextToken());

            receiverStack.push(new Tower(height, i + 1));
        }

        while(!receiverStack.empty()) {

            Tower receiver = receiverStack.pop();

            if(senderStack.empty()) {
                senderStack.push(receiver);
                continue;    
            }

            while(receiver.height > senderStack.peek().height) {
                Tower sender = senderStack.pop();
                answer[sender.idx - 1] = receiver.idx;

                if(senderStack.empty()) {
                    break;
                }
            }

            senderStack.push(receiver);
        }

        
        while(!senderStack.empty()) {
            Tower sender = senderStack.pop();
            answer[sender.idx - 1] = 0;
        }
        

        for(int a : answer) {
            System.out.print(a + " ");
        }
    }

    public static class Tower {
        public int idx;
        public int height;

        public Tower(int height, int idx) {
            this.idx = idx;
            this.height = height;
        }
    }
}
