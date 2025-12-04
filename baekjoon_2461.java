import java.io.*;
import java.util.*;

public class baekjoon_2461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<PriorityQueue<Long>> classes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            classes.add(pq);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.value, b.value)
        );

        long maxValue = Long.MIN_VALUE;
        long answer = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            long first = classes.get(i).poll();
            pq.add(new Node(first, i));
            maxValue = Math.max(maxValue, first);
        }

        while (true) {
            Node minNode = pq.poll();

            answer = Math.min(answer, maxValue - minNode.value);

            if (classes.get(minNode.classIdx).isEmpty()) {
                break;
            }
            long nextValue = classes.get(minNode.classIdx).poll();

            if (nextValue > maxValue) {
                maxValue = nextValue;
            }

            pq.add(new Node(nextValue, minNode.classIdx));
        }

        System.out.println(answer);
    }

    static class Node {
        long value;   // 현재 학생 점수
        int classIdx; // 어느 반인지
        Node(long value, int classIdx) {
            this.value = value;
            this.classIdx = classIdx;
        }
    }
}
