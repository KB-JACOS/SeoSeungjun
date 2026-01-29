    import java.util.*;
    import java.io.*;

    public class Main {

        static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};    //상 우 하 좌
        
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

            int N = Integer.parseInt(br.readLine()); 
            int find = Integer.parseInt(br.readLine()); 

            int[][] arr = new int[N+1][N+1]; 

            int r = N / 2 + 1;
            int c = N / 2 + 1;
            int d = 0;
            int num = 1;
            arr[r][c] = num;
            
            int[] cur = {r, c, num, d};
            for(int i=1;i<=N;i++){
                
                if(i < N) {
                    write(cur, arr, i);
                    write(cur, arr, i);
                }

                else if(i == N) {
                    write(cur, arr, N-1);
                }
            }

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();

            int findR = 0;
            int findC = 0;
            for(int i=1;i<=N;i++) {
                for(int j=1;j<=N;j++) {
                    if(find == arr[i][j]) {
                        
                        findR = i; 
                        findC = j;
                    }
                    sb.append(arr[i][j] + " ");
                }
                sb.append("\n");
            }
            sb.append(findR + " "+ findC + "\n");
            
            bw.write(sb.toString());
            
            bw.flush();
            bw.close();
        }

        public static void write(int[] cur, int[][] arr, int distance) {
            
            int r = cur[0]; 
            int c = cur[1];
            int num = cur[2]; 
            int d = cur[3];

            for(int i=0;i<distance;i++) {
                r += dr[d];
                c += dc[d];

                arr[r][c] = ++num;
            }

            d++; 
            d %= 4;

            cur[0] = r;
            cur[1] = c; 
            cur[2] = num;
            cur[3] = d;
        }
    }