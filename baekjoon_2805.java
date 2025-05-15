import java.util.*;
import java.io.*;


public class baekjoon_2805
    public static void main(String[] args) throws Exception { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M, N; 

        StringTokenizer st = new StringTokenizer(br.readLine()); 

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());

        int[] tree = new int[N];

        int left = 0, right = Integer.MIN_VALUE, mid = 0;
        for(int i=0;i<N;i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, tree[i]);
        }

        int result = 0;
        while(left <= right) {
            mid = (left + right) / 2;

            long sum = 0;
            for(int t : tree) {
                if(mid < t) sum += t - mid;
            }

            if(sum >= M){
                result = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}