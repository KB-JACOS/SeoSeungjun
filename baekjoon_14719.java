import java.util.*;
import java.io.*;

public class Main {

    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] arr = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<W;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;

        int result = 0;
        for(int i=0;i<W;i++) {
            int leftMax=0, rightMax=0; 

            for(int l =0;l < i;l++) {
                leftMax = Math.max(arr[l], leftMax);
            }

            for(int r = i + 1; r < W ; r++) {
                rightMax = Math.max(arr[r], rightMax);
            }

            int min = Math.min(leftMax, rightMax); 

            if(min > arr[i]) result += min - arr[i];
        }

        result += calcWater(left, right, arr);
        System.out.println(result);
    }
    
        
        
    public static int calcWater(int left, int right, int[] arr) {
        
        if(left == right || right - left < 2 ) {
            return 0;
        }

        int height = Math.min(arr[left], arr[right]);

        int result = 0; 
        
        for(int i=left + 1;i<right;i++) {
            result += height - arr[i];
        }

        return result;
    }
}
