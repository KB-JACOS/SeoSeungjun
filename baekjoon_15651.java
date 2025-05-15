import java.util.*;
import java.io.*;

class baekjoon_15651 {
    static int M, N;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception { 
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M+1];

        recur(arr, 0);
        
        bw.flush();
        bw.close();
    }


    public static void recur(int[] arr, int depth) throws Exception {
        if(depth == M) {
            print(arr);
            return;
        }

        for(int i=1;i<=N;i++) {
            arr[depth+1] = i;
            recur(arr, depth+1);
            arr[depth+1] = 0;
        }
    }
    
    public static void print(int[] arr) throws Exception {
        
        for(int i=1;i<=M;i++) {
            bw.write( Integer.toString(arr[i]) + " " );
        }
        bw.write("\n");
    }
}