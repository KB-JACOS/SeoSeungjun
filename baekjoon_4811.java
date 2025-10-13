
import java.io.*;
import java.util.*;

public class baekjoon_4811 {
    
    static long[][] memo = new long[31][31];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = false;
        List<Integer> arr = new ArrayList<>();

        for(int i=0;i<=30;i++) {
            for(int j=0;j<=30;j++) {
                memo[i][j] = -1;
            }
        }

        while(!flag){
            int num = Integer.parseInt(br.readLine());
            if(num == 0) break;
            arr.add(num);
        }


        for(int i=0;i<arr.size();i++){
            System.out.println(search(arr.get(i), 0));
        }
    }

    /**
     * @param one : 1개 알약 수
     * @param half : 반개 알약 수
     * @return
     */
    public static long search(int one, int half) {
        if(one == 0 && half == 0) {
            return 1;
        }

        if(memo[one][half] != -1) {
            return memo[one][half];
        }

        long tempCnt = 0;

        // w를 선택할 수 있는 경우
        if(one > 0) {
            tempCnt += search(one-1, half+1);
        }

        // h를 선택할 수 있는 경우
        if(half > 0) {
            tempCnt += search(one, half-1);
        }

        memo[one][half] = tempCnt;
        return tempCnt;
    }    
}
