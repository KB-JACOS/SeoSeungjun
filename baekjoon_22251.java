import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_22251 {

    static int[][] num = {
        {0,1,2,3,4,5,-1},       //0
        {5,4,-1,-1,-1,-1,-1},   //1
        {0,5,6,2,3,-1,-1},      //2
        {0,5,6,4,3,-1,-1},      //3
        {1,5,6,4,-1,-1,-1},     //4
        {0,1,6,4,3,-1,-1},      //5
        {0,1,2,3,4,6,-1},       //6
        {0,5,4,-1,-1,-1,-1},    //7
        {0,1,2,3,4,5,6},        //8
        {0,1,5,6,4,3,-1},       //9
    };

    static int[] nums = new int[10];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 7비트 변환
        for(int i=0;i<10;i++) {
            int n = 0;
            for(int j=0;j<7;j++) {
                if(num[i][j] == -1) continue;
                n |= (1 << (6 - num[i][j]));
            }
            nums[i] = n;
        }

        int answer = 0;

        for(int i=1;i<=N;i++) {

            if(i == X) continue;

            int diff = 0;

            int a = X;
            int b = i;

            for(int j=0;j<K;j++) {

                int d1 = a % 10;
                int d2 = b % 10;

                diff += Integer.bitCount(nums[d1] ^ nums[d2]);

                if(diff > P) break; 

                a /= 10;
                b /= 10;
            }

            if(diff <= P) answer++;
        }

        System.out.println(answer);
    }
}
