import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        for(int testCase=0;testCase<10;testCase++) {
            int n = Integer.parseInt(sc.nextLine());
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int[] arr = new int[n];
            for(int i=0;i<n;i++) arr[i] = Integer.parseInt(st.nextToken());
            System.out.println(String.format("#%d ", testCase+1) + calc(arr));
        }
	}
    
    public static int calc(int[] arr) {
        int sum = 0;
		for(int i=2;i<arr.length-2;i++){
            int available = 0;
			int highest = Math.max( Math.max(arr[i-1], arr[i+1]), Math.max(arr[i-2], arr[i+2]));
            if(highest < arr[i] ) available = arr[i] - highest;
            
            sum += available;
        }
        return sum;
    }
}