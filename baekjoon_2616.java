import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon_2616 {

    static int[] parent, rank;
    static final long BASE = 1000000L; // N 최대값보다 크게

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        parent = new int[N+1];
        rank = new int[N+1];
        Arrays.fill(rank, 1);

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        Set<Long> banned = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new int[]{0, i, cost});
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            banned.add(encode(s, e));
            banned.add(encode(e, s));
        }

        if(M < 2) {
            System.out.println("YES");
            return;
        }

        for (int i = 1; i < N; i++) {
            if (!banned.contains(encode(i, i+1))) {
                pq.add(new int[]{i, i+1, 0});
            }
        }

        if (!banned.contains(encode(N, 1))) {
            pq.add(new int[]{N, 1, 0});
        }

        long total = 0;
        int edgeCount = 0;

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();
            int s = cur[0];
            int e = cur[1];
            int w = cur[2];

            if (find(s) != find(e)) {
                union(s, e);
                total += w;
                edgeCount++;
            }

            if (edgeCount == N) break;
        }

        if (edgeCount != N) {
            System.out.println("NO");
            return;
        }

        if (total <= K) System.out.println("YES");
        else System.out.println("NO");
    }

    static long encode(int a, int b) {
        return a * BASE + b;
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) return;

        if (rank[ra] > rank[rb]) {
            parent[rb] = ra;
        } else if (rank[rb] > rank[ra]) {
            parent[ra] = rb;
        } else {
            parent[rb] = ra;
            rank[ra]++;
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}