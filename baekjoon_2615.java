import java.util.*;
import java.io.*;

public class baekjoon_2615 {
    
	static int N = 19;
	static final int B = 1, W = 2; 

    public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		int[][] board = new int[N][N];
		
		Queue<int[]> queue = new LinkedList<>();
		for(int i=0;i<N;i++) {

			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] > 0) queue.add(new int[]{i, j});
				
			}
		}

		boolean fail = true;

		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int r = cur[0];
			int c = cur[1];

			if(check(r, c, board)) {
				System.out.println(board[r][c]);
				System.out.printf("%d %d\n", r+1, c+1);
				fail = false;
				break;
			}
		}

		if(fail) System.out.println("0");
	}


	static boolean check(int r, int c, int[][] board) {
    int color = board[r][c];
    int[][] dir = {{0,1},{1,0},{1,1},{-1,1}};

    for (int[] d : dir) {
        int cnt = 0;

        for (int i = 0; i < 5; i++) {
            int nr = r + d[0] * i;
            int nc = c + d[1] * i;

            if (nr < 0 || nr >= 19 || nc < 0 || nc >= 19) break;
            if (board[nr][nc] != color) break;
            cnt++;
        }

        if (cnt == 5) {
            
            int pr = r - d[0];
            int pc = c - d[1];

        
            int nr = r + d[0] * 5;
            int nc = c + d[1] * 5;

            if ((pr < 0 || pr >= 19 || pc < 0 || pc >= 19 || board[pr][pc] != color) &&
                (nr < 0 || nr >= 19 || nc < 0 || nc >= 19 || board[nr][nc] != color)) {
                return true;
            }
        }
    }
    return false;
}



}
