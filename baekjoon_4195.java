import java.io.*;
import java.util.*;

public class baekjoon_4195 {
    static int[] parent;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());

            parent = new int[F * 2];
            size = new int[F * 2];

            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            Map<String, Integer> map = new HashMap<>();
            int id = 0;

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (!map.containsKey(a)) map.put(a, id++);
                if (!map.containsKey(b)) map.put(b, id++);

                int root = union(map.get(a), map.get(b));
                sb.append(size[root]).append("\n");
            }
        }

        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
            size[a] += size[b];
        }

        return a;
    }
}
