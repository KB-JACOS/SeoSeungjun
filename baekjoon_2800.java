import java.util.*;
import java.io.*;

public class baekjoon_2800 {
    
    static ArrayList<int[]> bracketSet = new ArrayList<>();
    static String exp;
    static Set<String> result = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        exp = br.readLine();
        char[] arr = exp.toCharArray();

        Stack<Integer> stack = new Stack<>();

        int cnt = 0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i] == '('){
                stack.push(i);
            }

            if(arr[i] == ')'){
                int open = stack.pop();
                int close = i; 
                bracketSet.add(new int[] {open, close});
                cnt++;
            }
        }        

        int set = (1 << cnt) - 1;  //set = 괄호의 개수 만큼 1로 채우기 10개 이하이므로 가능 

        for(int subset = set; subset != 0;  subset = (subset-1)&set){
            String binary = String.format("%" + cnt + "s", Integer.toBinaryString(subset)).replace(' ', '0');
            
            deleteBracket(binary.toCharArray());
        }

        List<String> resultList = new ArrayList<>(result);
        Collections.sort(resultList);
        for(String s : resultList){
            System.out.println(s);
        }

    }

    public static void deleteBracket(char[] subset) {
        StringBuilder sb = new StringBuilder(exp);
        for(int i=0;i<subset.length;i++) {
            if(subset[i] == '1' ) {
                int[] indexes = bracketSet.get(i);
                sb.setCharAt(indexes[0], ' ');
                sb.setCharAt(indexes[1], ' ');
            }
        }

        result.add(sb.toString().replaceAll(" ", ""));
    }
    
}
