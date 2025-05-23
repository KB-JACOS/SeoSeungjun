import java.util.*;
import java.io.*;

class swea_22979 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T;
		T=Integer.parseInt(sc.nextLine());
		

		for(int test_case = 1; test_case <= T; test_case++) {	
            String str = sc.nextLine();

            StringBuilder sb = new StringBuilder(str);
            int K = Integer.parseInt(sc.nextLine());
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            for(int i=0;i<K;i++) {
                int op = Integer.parseInt(st.nextToken());
                operation(op, sb, str.length());
            }
            bw.write(sb.toString() + "\n");
		}

        bw.flush();
        bw.close();
	}

    public static void operation(int op, StringBuilder sb, int strLen) {
        int opCnt =  Math.abs(op)% strLen;
        
        for(int i =0;i<opCnt;i++) {
            if(op > 0) {
                char temp = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(temp);
            }

            else {
                char temp = sb.charAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                sb.insert(0, temp);
            }
        }
    }


}