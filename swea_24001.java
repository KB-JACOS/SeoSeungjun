import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
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
            searchOnlyCommand(cmd);
            System.out.println(max);
            max = 0;
      }
   }
    
    public static void searchOnlyCommand(char[] cmd) {
        
        int L = 0, R = 0;
        int questionMarkCnt = 0;
        for(int i=0;i<cmd.length;i++){
            if(cmd[i] == 'R'){
                R++;
            }
            else if (cmd[i] == 'L'){
                L++;
            }
            
            else {
                questionMarkCnt++;
                
            }
            //System.out.print( " " + distance);
            max = Math.max(Math.abs(R-L) + questionMarkCnt, max);
        }
    }
 }