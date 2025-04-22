import java.util.*;

public class baekjoon_12919 {
    static Queue<char[]> queue = new LinkedList<>();
    
    static String T, S;
    static int result = 0;
    public static void main(String args[]) throws Exception{
        
        Scanner sc = new Scanner(System.in);

        S = sc.nextLine();
        T = sc.nextLine();

        recur(T);

        System.out.println(result);
        sc.close();
    }

    public static void recur(String str) {
        
        if(str.equals(S)){
            // System.out.println(str);
            result = 1;
            return;
        } 
        
        if(str.length() == S.length()){
            return;
        }

        if(str.endsWith("A")){
            recur(str.substring(0, str.length()-1));
        }

        if (str.startsWith("B")) {
            recur(new StringBuilder(str.substring(1)).reverse().toString());
        }        

    }
}