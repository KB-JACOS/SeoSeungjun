import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        // 1, 2, 3 개수 세기
        int[] cnt = new int[4];
        for (int x : arr) cnt[x]++;

        // 가능한 6가지 순서
        int[][] orders = {
            {1,2,3}, {1,3,2},
            {2,1,3}, {2,3,1},
            {3,1,2}, {3,2,1}
        };

        int answer = Integer.MAX_VALUE;

        for (int[] order : orders) {
            int[][] mis = new int[4][4]; // mis[i][j] = i 구역에 j가 몇 개 있는지

            // 구역 범위 설정
            int start = 0;
            for (int k = 0; k < 3; k++) {
                int shape = order[k];
                int len = cnt[shape];
                int end = start + len;

                for (int i = start; i < end && i < N; i++) {
                    mis[shape][arr[i]]++;
                }

                start = end;
            }

            // 교환 계산
            int swaps = 0;

            // 1. 쌍교환 (A↔B)
            int direct12 = Math.min(mis[1][2], mis[2][1]);
            swaps += direct12;
            mis[1][2] -= direct12;
            mis[2][1] -= direct12;

            int direct13 = Math.min(mis[1][3], mis[3][1]);
            swaps += direct13;
            mis[1][3] -= direct13;
            mis[3][1] -= direct13;

            int direct23 = Math.min(mis[2][3], mis[3][2]);
            swaps += direct23;
            mis[2][3] -= direct23;
            mis[3][2] -= direct23;

            // 2. 남은 것은 3-cycle (세 방향이 꼬인 경우)
            int remain = 0;
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (i != j) remain += mis[i][j];
                }
            }
            // 세 개가 서로 뒤바뀐 경우: 3-cycle 하나당 2번 교환 필요
            swaps += (remain / 3) * 2;

            answer = Math.min(answer, swaps);
        }

        System.out.println(answer);
    }
}
