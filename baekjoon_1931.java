import java.util.*;
import java.io.*;

class baekjoon_1931 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] schedule = new int[N][2];

		StringTokenizer  st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(schedule, (o1, o2) -> {
			if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]); 
			else return Integer.compare(o1[1], o2[1]);
		});
		
		int cnt = 0;
        int endTime = 0;

        for (int i = 0; i < N; i++) {
            if (schedule[i][0] >= endTime) {
                endTime = schedule[i][1];
                cnt++;
            }
        }

		System.out.println(cnt);
	}
}
