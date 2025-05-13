import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

/*
 * 백트래킹으로 했더니 O(2^n)이라 최악에 2^50인 상황 
 * memoization을 해야할거 같은데 감이 안온다.. 
 * dp 일거 같은 예상
 * 
 */

class Solution {
    
    static int distance = 0, max = 0;
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
        sc.nextLine();
        
		for(int test_case = 1; test_case <= T; test_case++) {
			String command = sc.nextLine();
            char[] cmd = command.toCharArray();
            int qCnt = searchOnlyCommand(cmd);
            max = Math.max(Math.abs(distance) + qCnt, max);
            System.out.println( max);
            distance = 0;
            max = 0;
		}
	}
    
    public static int searchOnlyCommand(char[] cmd) {
        int questionMarkCnt = 0;
        for(int i=0;i<cmd.length;i++){
            if(cmd[i] == 'R'){
                distance+=1;
            }
            else if (cmd[i] == 'L'){
                distance-=1;
            }
            
            else {
                questionMarkCnt++;
            }
            //System.out.print( " " + distance);
            max = Math.max(Math.abs(distance), max);
        }
        
        
        return questionMarkCnt;
    }
 }