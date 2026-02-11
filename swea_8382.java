import java.io.*;
import java.util.*;

public class swea_8382 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=T;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            
            int rd = 0 - r1;
            int cd = 0 - c1; 

            
            r1 += rd;
            c1 += cd; 
            r2 += rd;
            c2 += cd;

            
            r2 = Math.abs(r2);
            c2 = Math.abs(c2);

            int goon = Math.abs(r2 + c2) / 2;
            int distance = (goon * 2) + Math.abs(r2 - goon) + Math.abs(c2 - goon);
            // sb.append(String.format("%d %d - ", Math.abs(r2 - goon), Math.abs(c2 - goon)));

            sb.append(String.format("#%d %d\n", tc, distance));
        }

        System.out.println(sb.toString());
    }



}
