import java.util.*;
import java.io.*;

public class Main {

    private static int cursor;
    private static Stack<Character> left = new Stack<Character>();
    private static Stack<Character> right = new Stack<Character>();

    public static void main(String[] args) throws Exception {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String lineString = br.readLine();
    
        for(int i=0;i<lineString.length(); i++){
            left.push(lineString.charAt(i));
        }

        int cnt = Integer.parseInt(br.readLine());
        for(int i=0;i<cnt;i++){
            String cmdLine = br.readLine();
            
            char cmd = cmdLine.charAt(0);

            if(cmd == 'L'){
                if(!left.isEmpty()){
                    char c = left.pop();
                    right.push(c);
                }
            }

            else if(cmd == 'D'){
                if(!right.isEmpty()){
                    char c = right.pop();
                    left.push(c);
                }
            }
            
            else if (cmd == 'B'){
                if(!left.isEmpty()){            
                    left.pop();
                }
            }

            else if (cmd == 'P'){
                left.push(cmdLine.charAt(2));
            }
        }

        while(!left.isEmpty()){
            char c = left.pop();
            right.push(c);
        }

        while(!right.isEmpty()){
            bw.write(right.pop());
        }

        bw.flush();
		bw.close(); 

    }
}