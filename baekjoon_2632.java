import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_2632 {
    
    public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] a = new int[M+1];
		int[] b = new int[N+1];

		for(int i=1;i<=M;i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1;i<=N;i++) {
			b[i] = Integer.parseInt(br.readLine());
		}

		int[][] as = new int[a.length+1][a.length+1];
		int[][] bs = new int[b.length+1][b.length+1];

		int[] ac = new int[T+1];
		int[] bc = new int[T+1];
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<M-1;j++) {
				int idx = (j + i) % M + 1;
				as[i][j+1] = as[i][j] + a[idx];

				// System.out.print(as[i][j+1] + " ");
				if(as[i][j+1] <= T)  {
					ac[as[i][j+1]] += 1;
				}
			}
			
			// System.out.println();
		}

		int sumA = 0;
		for(int i=1;i<=M;i++) {
			sumA += a[i];
		}
		if(sumA <= T) { ac[sumA] +=1; }

		// System.out.println("===");
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N-1;j++) {
				int idx = (j + i) % N + 1;
				bs[i][j+1] = bs[i][j] + b[idx];

				// System.out.print(bs[i][j+1] + " ");
				if(bs[i][j+1] <= T) {
					bc[bs[i][j+1]] += 1;
				}
			}

			// System.out.println();
		}

		int sumB = 0;
		for(int i=1;i<=N;i++) {
			sumB += b[i];
		}
		if(sumB <= T) { bc[sumB] +=1; }

		// System.out.println(Arrays.toString(ac));
		// System.out.println(Arrays.toString(bc));
		int cnt = 0;

		for(int i=0;i<T;i++) {
			cnt += ac[i] * bc[T-i];
		}

		cnt += ac[T];
		cnt += bc[T];

		System.out.println(cnt);
	}
}
