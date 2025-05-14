import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
    
        Scanner sc = new Scanner(System.in); 
        int T = Integer.parseInt(sc.nextLine());
        
        for(int testCase = 0;testCase<T;testCase++){
            int n = Integer.parseInt(sc.nextLine());
            
            int[] preferA = new int[n], preferB = new int[n];
            char[] belongTo = new char[n+1];
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            for(int i=0;i<n;i++) preferA[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(sc.nextLine());
            for(int i=0;i<n;i++) preferB[i] = Integer.parseInt(st.nextToken());
            System.out.println(select(preferA, preferB, belongTo));
        }
	}

    public static String select(int[] preferA, int[] preferB, char[] belongTo){
        int N = preferA.length;

        for(int i = 0;i <N;i++){
            for(int j=0;j<N;j++){
                if( belongTo[preferA[j]] == 0) {
                    belongTo[preferA[j]] = 'A';
                    break;
                }
            }
            
            for(int j=0;j<N;j++){
                if( belongTo[preferB[j]] == 0) {
                    belongTo[preferB[j]] = 'B';
                    break;
                }
            }
        }
        
        return new String(belongTo, 1, N );
    }
   
}