import java.io.*;
import java.util.*;


/**
 * <baekjoon_11659> -   구간합 구하기 4
 * 부분합 문제
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(i == 0) continue;
            arr[i] += arr[i-1];
            
        }
        
        int[] result = new int[M];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(a == 1) result[i] = arr[b-1];
            else {
                result[i] = arr[b-1] - arr[a-2];
            }
            
        }

        for(int i=0;i<M;i++){
            System.out.println(result[i]);
        }
    }

}