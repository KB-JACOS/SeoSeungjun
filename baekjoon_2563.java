import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_2563 {

    static int[][] map = new int[101][101]; 
    static int black = 0;

    public static void main(String[] args) throws Exception { 
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());


        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()); 
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            coloring(r, c);
        }

        System.out.println(black);
    }   

    public static void coloring(int r, int c) {
        
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                int nr = r + j;
                int nc = c + i;

                if(map[nr][nc] == 0) black++;
                map[nr][nc] = 1;
            }
        }
    }
}