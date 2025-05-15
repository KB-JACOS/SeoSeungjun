import java.util.*;
import java.io.*;

class Main {

    static Set<String> set= new HashSet<>();
    static int M, N;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception { 
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M+1];

        recur(new boolean[N+1], 0);
        
        bw.flush();
        bw.close();
    }


    public static void recur(boolean[] arr, int depth) throws Exception {
        if(depth == M) {
            print(arr);
        }

        for(int i=1;i<=N;i++) {
            if(arr[i]) continue;

            arr[i] = true;
            if(!set.contains(Arrays.toString(arr))) {
                set.add(Arrays.toString(arr));
                recur(arr, depth+1);
            }
            arr[i] = false;
        }
    }
    
    public static void print(boolean[] arr) throws Exception {
        char[] charArr = new char[M];
        int cnt = 0;
        for(int i=1;i<=N;i++) {
            if(!arr[i]) continue;

            charArr[cnt] = Integer.toString(i).charAt(0);
            cnt++;
        }
        Arrays.sort(charArr);
        String result = new String(charArr);
        
        System.out.println(String.join(" ", result.split("")));
    }
}