    import java.util.*;
    import java.io.*;

    public class Main {
        public static List<Integer> list = new ArrayList<>();
        public static boolean flag;
        public static boolean[] visited;
        public static int[][] arr;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int N = Integer.parseInt(br.readLine());

            int size = 2 * N - 1;
            arr = new int[N+1][size+1];
            
            star(1, N, N);

            for(int i=1;i<=N;i++) {
                for(int j=1;j<=size;j++) {
					bw.write((arr[i][j] == 1)?'*':' ');
                }
                bw.write("\n");
            }

            bw.flush();
			bw.close();
        }

        public static void star(int r, int c, int n) {
            
            if(n == 3) {

                arr[r][c] = 1;
                int secondR = r + 1;
                int secondC = c - 1;
                arr[secondR][secondC] = 1;
                arr[secondR][secondC+2] = 1;

                for(int i=c-2;i<c+3;i++) {
                    arr[r+2][i] = 1;
                }

            }
            else {
                star(r+n/2, c-n/2, n/2); // left 
                star(r+n/2, c+n/2, n/2); // right
                star(r, c, n/2);         // middle
            }   
        }   
    }