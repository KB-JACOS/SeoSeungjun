
import java.util.*;
import java.io.*;


public class swea_1249 {
	
	static int U=0, D=1, L=2, R=3;
	
	public static int[] dr = {0, 0, -1, 1}, dc = {-1, 1, 0, 0};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		List<Integer> result = new ArrayList<>();
		for(int t=1;t<=testCase;t++) {
			
			int N = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[N][N]; 
			boolean[][] visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
				char[] line = br.readLine().toCharArray();
				
				for(int j=0;j<N;j++) {
					arr[i][j] = line[j] - '0';
				}
			}
			
			PriorityQueue<int[]> queue = new PriorityQueue<>( (o1, o2) -> {
                return Integer.compare(o1[2], o2[2]);
            });
			
			queue.add(new int[] {0, 0, 0});
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				int r = cur[0];
				int c = cur[1];
				int w = cur[2];
                
                boolean finish = false;
				
				for(int d=0;d<4;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
					
					int nw = w + arr[nr][nc];
					
                    if(visited[nr][nc]) {
                        continue;
                    }
					
					if(nr == N-1 && nc == N-1) {
                        result.add(nw);
                        finish=true;
                        break;
					}
                    visited[nr][nc] = true;
					
					queue.add(new int[] {nr, nc, nw});
				}

                if(finish) {
                    break; 
                }
			}
		}
		
		for(int t=1;t<=testCase;t++) {
			System.out.printf("#%d %d\n", t, result.get(t-1));
		}
		
	}
	
	
}
