import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> big = new PriorityQueue<>();
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0;i<N;i++) {
            
            int num = arr[i];

            if(i == 0) {
                small.add(num);
                bw.write(num + "\n");
                // print(small, big);
                continue;
            }

            else if(i == 1) {
                if(small.peek() < num) big.add(num);
                else {
                    small.add(num);
                    big.add(small.poll());
                }
                // print(small, big);
                bw.write( Math.min(small.peek(), big.peek()) + "\n");
                continue;
            }

            if(small.peek() < num) {
                big.add(num);
            }

            else {
                small.add(num);
            }

            if(small.size() < big.size()) {
                small.add(big.poll());
            }

            else if (small.size() > big.size() + 1){
                big.add(small.poll());
            }
            
            // print(small, big);
            bw.write(small.peek() + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void print(PriorityQueue a, PriorityQueue b) {
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println();
    }
}
