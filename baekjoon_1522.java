import java.util.*;
import java.io.*;

/**
<<baekjoon 1522 문자열 교환>>
a의 개수만큼 연속되어야 한다. 
슬라이딩 윈도우를 a의 개수만큼 잡으면 우리가 잡은 슬라이딩 범위가 a로 연속된 구간이라고 가정할 수 있다. 
그러면 해당 구간의 b의 개수만큼만 switching 해주면 연속된 a의 구간이라고 할 수 있다. 

ababa 이면 a개수 = 3
[aba]ba 구간에서는 b가 1개 이므로 1
a[bab]a 구간에서는 b가 2개 이므로 2
ab[aba] 구간에서는 b가 1개 이므로 1
이 값중 최소 값은 1임
 */

public class baekjoon_1522 {
    

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int aCnt = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == 'a') aCnt++;
        }

        int min = Integer.MAX_VALUE;
        for(int i=0;i<str.length();i++){
            int cnt = 0;
            for(int j=i;j<(i+aCnt);j++){
                int index = j % str.length();
                
                if(str.charAt(index) == 'b') cnt ++;
            }

            // System.out.printf("%d %d %d \n", i, (i+aCnt)%str.length(), cnt );
            min = Math.min(cnt, min);
        }

        System.out.println(min);
        
    }
}