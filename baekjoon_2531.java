import java.util.*;
import java.io.*;

/**
 * <<baekjoon_2531 - 회전 초밥>>
 * Stack을 활용하여 모든 조합을 탐색하려고 했음 -> 백트랙킹
 * 이렇게 하면 O(N^K)의 시간 복잡도로 어림도 없음
 * 
 * [solution]
 * 슬라이딩 윈도우 방식으로 순회하면서 0 - N-1까지 k크기의 윈도우만큼 검사하여 최대로 다양한 조합을 찾으면 됨
 * 
 * 시간 복잡도
 * N = 30000길이의 배열을 K개만큼 순회하기 때문에 O(N*K) = 90,000,000 이므로 1초내 탐색 가능
 */

public class baekjoon_2531 {
    

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N, D, K, C;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        for(int i=0;i<N;i++){
            sushi[i] = Integer.parseInt(br.readLine());
        }
        
        int max = 0;
        for(int i=0;i<N;i++){
            boolean[] eat = new boolean[D+1];
            int cnt = 0;
            for(int j=0;j<K;j++){
                int index = (i + j >= N) ? (i+j)%N: i+j;
                int value = sushi[index];
                if(!eat[value]){
                    cnt ++;
                    eat[value] = true;
                }
            }
            
            if(!eat[C]) {
                cnt++;
                eat[C] = true;
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
}