import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int N = Integer.parseInt(br.readLine());

            List<Integer> answer = new ArrayList<>();

            for(int i=0;i<N;i++) {
                
                int M = Integer.parseInt(br.readLine());
                int sum = 0;
                int[] arr = new int[M];

                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int j=0;j<M;j++) {
                    arr[j] = Integer.parseInt(st.nextToken());
                    sum += arr[j];
                }

                boolean[] dp = new boolean[sum+1];
                dp[0] = true;

                for(int j=0;j<M;j++) {
                    List<Integer> addList = new ArrayList<>();

                    for(int k=0;k<dp.length;k++) {
                        if(dp[k]) {
                            addList.add(k + arr[j]);
                        }
                    }

                    for(Integer index : addList) {
                        dp[index] = true;
                    }
                }

                int cnt = 0;
                for(int j=0;j<dp.length;j++) {
                    if(dp[j]){
                        cnt++;
                    }
                }

                answer.add(cnt);
            }

            for(int i=0;i<N;i++) {
                System.out.printf("#%d %d\n", i+1, answer.get(i));
            }
            
        }
}
