import java.util.*;
import java.io.*;

public class Main {
	
	static final int X = 1;
	static int R, C;
	static int[][] maps; 
	static int cnt = 0; 
	
	// 우상, 우, 우하
	static int[] dr = {-1, 0, 1}, dc = {1, 1, 1};
	static final int RU = 0; 
	static final int RR = 0; 
	static final int RD = 0; 
	static boolean[][][] dp;


	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maps=new int[R][C];
		dp = new boolean[R][C][3];

		for(int i=0;i<R;i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				if(arr[j] == 'x') {
					maps[i][j] = X;
				}
				for(int k=0;k<3;k++) {
					dp[i][j][k] = true;
				}
			}
		}

		for(int i=0;i<R;i++) {
			dfs(i, 0);

		}
		System.out.println(cnt);
	}

	public static boolean dfs(int r, int c) {
		for(int i=0;i<3;i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if(nr < 0 || nc < 0 || nr >= R || nc >= C || maps[nr][nc] == X || !dp[nr][nc][i]){
				continue;
			}

			maps[nr][nc] = X;

			if(nc == C-1) {
				cnt++;
				return true;
			}
			
			if(dfs(nr, nc)) {
				return true;
			}
			dp[nr][nc][i] = false;
			maps[nr][nc] = 0;
 		}

		return false;
	}
}
