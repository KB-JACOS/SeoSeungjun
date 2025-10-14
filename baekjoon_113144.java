import java.io.*;
import java.util.*;

public class BOJ_13144 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long res = 0;
		int left = 0, right = 0;
		int[] visited = new int[100001];

		while (right < N) {
				if (visited[arr[right]] == 0) {
				visited[arr[right]]++;
				res += (right - left + 1); 
				right++;
			} else {	
				while (visited[arr[right]] > 0) {
					visited[arr[left]]--;
					left++;
				}
			}
		}

		System.out.println(res);
	}
}
