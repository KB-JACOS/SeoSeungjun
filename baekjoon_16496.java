    import java.util.*;
    import java.io.*;
import java.math.BigDecimal;

    public class Main {

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine()); 
            
            List<Integer> arr =new ArrayList<>();

            for(int i=0;i<N;i++) {
                int num = Integer.parseInt(st.nextToken());
                arr.add(num);
            }

            Collections.sort(arr, (o1, o2) -> {
                String a = String.valueOf(o1) + String.valueOf(o2);
                String b = String.valueOf(o2) + String.valueOf(o1);

                BigDecimal aa = new BigDecimal(a);
                BigDecimal bb = new BigDecimal(b);

                if(aa.compareTo(bb) < 0) {
                    return 1;
                }

                else if(aa.compareTo(bb)== 0) return 0;

                else return -1;

            });
            

            boolean flag = true;
            for(int num : arr) {
                if(num != 0) { 
                    flag = false; 
                    break;
                }
            }

            if(flag) {
                System.out.println(0);
            }
            else {
                for(int num : arr) {
                    System.out.print(num);
                }
                System.out.println();
            }

            

        }

}
