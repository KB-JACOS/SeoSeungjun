import java.util.*;
import java.io.*;

class Main {

    static boolean[] visited;
    static int M, N;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception { 
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M+1];
        visited = new boolean[N+1];

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
            if(!visited[i]) {
                arr[depth+1] = i;
                visited[i] = true;
                recur(arr, depth+1);
                visited[i] = false;
                arr[depth+1] = 0;
            }
        }
    }
    
    public static void print(int[] arr) throws Exception {
        
        for(int i=1;i<=M;i++) {
            bw.write(String.format("%d ", arr[i]));
        }
        
        bw.write(String.format("\n"));
    }
}