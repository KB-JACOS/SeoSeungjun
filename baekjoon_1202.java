import java.util.*;
import java.io.*;

public class baekjoon_1202 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<int[]> dia = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            dia.add(new int[]{m, v});
        }

        int[] bag = new int[K];

        for(int i=0;i<K;i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag);

        // 보석을 무게 기준 정렬
        Collections.sort(dia, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        // 가격 기준 max heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        
        int ddx = 0;
        long result = 0;

        for(int i=0;i<K;i++) {

            // 현재 가방에 들어갈 수 있는 보석 추가
            while(ddx < N && dia.get(ddx)[0] <= bag[i]) {
                pq.add(dia.get(ddx));
                ddx++;
            }

            // 가장 비싼 보석 선택
            if(!pq.isEmpty()) {
                result += pq.poll()[1];
            }
        }

        System.out.println(result);
    }
}