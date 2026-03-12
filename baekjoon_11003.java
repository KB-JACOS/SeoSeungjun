import java.io.*;
import java.util.*;

public class baekjoon_11003 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<N;i++){

            // 뒤에서 큰 값 제거
            while(!dq.isEmpty() && arr[dq.peekLast()] > arr[i])
                dq.pollLast();

            dq.addLast(i);

            // 범위 밖 제거
            if(dq.peekFirst() <= i-L)
                dq.pollFirst();

            sb.append(arr[dq.peekFirst()]).append(" ");
        }

        System.out.println(sb);
    }
}