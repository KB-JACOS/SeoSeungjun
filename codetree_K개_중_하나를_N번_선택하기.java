import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class codetree_K개_중_하나를_N번_선택하기 {
    
    static int N, max = Integer.MIN_VALUE;
    // [0] - start || [1] - end
    static List<int[]> lines = new ArrayList<>();

    public static void main(String[] args) throws Exception {         

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        N = Integer.parseInt(br.readLine()); 

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); 
            int e = Integer.parseInt(st.nextToken()); 

            lines.add(new int[]{s, e}); 
        }
        
        find(0, new ArrayList<int[]>());

        System.out.println(max);
        

    }

    public static void find(int depth, List<int[]> selected) {
        if(depth == lines.size()) {
            max = Math.max(max, selected.size());
            return;
        }
        int[] cur = lines.get(depth); 
        int s = cur[0];
        int e = cur[1]; 

        boolean isContain = false; 
        for(int[] line : selected){ 
            if((s >= line[0] && s <= line[1]) || (e >= line[0] && s <= line[1])) {
                isContain = true;
                break;
            }
        }

        find(depth + 1, selected);

        if(!isContain) {
            selected.add(new int[] {s, e});
            find(depth+1, selected);
            selected.remove(selected.remove(selected.size() - 1 ));
        } 
    }
}
