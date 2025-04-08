import java.io.BufferedReader;
import java.io.InputStreamReader;

public class baekjoon_15989 {
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[][] arr = new int[10001][4];
		arr[1][1] = 1;
		arr[2][1] = 1;
		arr[2][2] = 1;
		arr[3][1] = 1;
		arr[3][2] = 1;
		arr[3][3] = 1;

		for (int i = 4; i <= 10000; ++i) {
			arr[i][1] = arr[i - 1][1];
			arr[i][2] = arr[i - 2][1] + arr[i - 2][2];
			arr[i][3] = arr[i - 3][1] + arr[i - 3][2] + arr[i - 3][3];
		}

		sb = new StringBuilder();
		int T = stoi(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			N = stoi(in.readLine());
			int sum = arr[N][1] + arr[N][2] + arr[N][3];
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}