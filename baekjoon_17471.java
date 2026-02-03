import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] arr;
    static int sum = 0;
    static int min = Integer.MAX_VALUE;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int v = Integer.parseInt(st.nextToken());
                graph.get(i).add(v);
            }
        }

        boolean[] visited = new boolean[N + 1];
        find(1, visited, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    // 부분집합 DFS
    static void find(int idx, boolean[] visited, int pop) {

        if (idx > N) {
            // 한 쪽이 비어 있으면 불가능
            if (pop == 0 || pop == sum) return;

            // 두 선거구 모두 연결되어야 함
            if (!isConnected(visited, true)) return;
            if (!isConnected(visited, false)) return;

            int diff = Math.abs((sum - pop) - pop);
            min = Math.min(min, diff);
            return;
        }

        // idx 선택
        visited[idx] = true;
        find(idx + 1, visited, pop + arr[idx]);

        // idx 미선택
        visited[idx] = false;
        find(idx + 1, visited, pop);
    }

    // flag = true / false 선거구 연결성 검사
    static boolean isConnected(boolean[] visited, boolean flag) {

        boolean[] check = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (visited[i] == flag) {
                q.add(i);
                check[i] = true;
                break;
            }
        }

        if (q.isEmpty()) return false;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (!check[next] && visited[next] == flag) {
                    check[next] = true;
                    q.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i] == flag && !check[i]) {
                return false;
            }
        }

        return true;
    }
}
