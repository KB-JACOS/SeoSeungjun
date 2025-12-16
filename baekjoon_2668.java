import java.io.*;
import java.util.*;

public class baekjoon_2668 {
    static int N, M;
    static boolean[] used;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int arr[] = new int[N+1];
        used = new boolean[N+1];

        for(int i=1;i<=N;i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }
        
        List<Integer> result = new ArrayList<>();

        for(int i=1;i<=N;i++) {
            find(arr, i, new boolean[N+1], result);
            if(used[i]) {
                continue;
            }
        }

        Collections.sort(result);

        System.out.println(result.size());

        for(int num : result) {
            System.out.println(num);
        }

    }

    public static int find(int[] arr,int n, boolean[] visited, List<Integer> result){

        if(used[n]) {           //이미 순환이 카운트 된 노드
            return -1;
        }

        if(visited[n]) {        //순환 하는곳을 찾으면
            visited[n] = true;
            result.add(n);
            return arr[n];
        }
        
        else if(arr[n] == n) {  //자기 자신을 가리키면
            visited[n] = true;
            result.add(n);
            used[n] = true;
            return -1; 
        }

        visited[n] = true;      //방문 처리
        int cur = find(arr, arr[n], visited, result);

        if(cur == n) {          // 내가 순환 시작점인 경우
            result.add(n);
            used[n] = true;
            return -1;
        }

        else if(cur == -1){     // 순환에 포함되지 않는 경우
            return cur;
        }
        
        else {                  // 순환에 포함되는 일부인 경우
            result.add(n);
            used[n] = true;
            return cur;
        }
    }
}
