import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine().trim();
        String T = br.readLine().trim();

        char[] arrT = T.toCharArray();
        char[] arrS = S.toCharArray();

        List<List<Integer>> memo = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) memo.add(new ArrayList<>());

        for (int i = 0; i < T.length(); i++) {
            int seq = arrT[i] - 'a';
            memo.get(seq).add(i);
        }

        // S에 있는 문자가 T에 하나도 없으면 불가능 → -1
        for (char c : arrS) {
            int seq = c - 'a';
            if (seq < 0 || seq >= 26 || memo.get(seq).isEmpty()) {
                System.out.println(-1);
                return;
            }
        }

        int cycle = 0;
        int cur = -1;

        for (char c : arrS) {
            int seq = c - 'a';
            List<Integer> idxs = memo.get(seq);

            int idx = binarySearch(idxs, cur);
            if (idx == -1) {
                
                cycle++;
                cur = -1;
                idx = binarySearch(idxs, cur);
            }
            cur = idxs.get(idx);
        }

        
        System.out.println(cycle + 1);
    }

    static int binarySearch(List<Integer> idxs, int lowerBound) {
        int l = 0, r = idxs.size() - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (idxs.get(mid) > lowerBound) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
