import java.io.*;
import java.util.*;

public class baekjoon_2473 {

    static long[] liquid;
    static long MIN = Long.MAX_VALUE;


    static int l1, l2, l3;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        liquid = new long[N];

        for(int i=0;i<N;i++){
            long a = Long.parseLong(st.nextToken());
            liquid[i] = a;
        }

        Arrays.sort(liquid);

        for(int i=0;i<N-2;i++){
            twoPoint(i+1, liquid.length-1, i);
        }
        
        System.out.println(liquid[l1] + " " + liquid[l2] + " " + liquid[l3]);
    }

    public static void twoPoint(int p1, int p2, int fix){
        
        long sum;
        long abs;

        while(p1<p2){
            
            sum = liquid[p1] + liquid[p2] + liquid[fix];
            
            abs = Math.abs(sum);
            if(abs < MIN){
                MIN = abs;
                l1 = fix; 
                l2 = p1;
                l3 = p2;

            }

            if(sum >= 0){
                p2 -= 1;
            }
            else{
                p1 += 1;
            }
            
        }
    }
}