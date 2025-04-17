import java.util.*;
import java.io.*;


/**
 << baekjoon_1697>>
 
 [solution]
  - 일단 최소를 구하는 문제라서 BFS를 떠올렸음 
  - 주어진 3개의 이동 가능한 방법으로 queue에 넣어서 탐색하면 됨
  
 [time-complexity]
    bfs로 탐색하므로 최로 방문한 경로가 최소한의 수로 방문한다고 할 수 있다.
    때문에 이미 방문한 노드를 방문할 필요 없으므로 memoization을 하면
    방문한 노드에 중복 방문하는 케이스를 줄일 수 있다. 
    그렇게 되면 최대 O(100000) 이므로 시간내에 탐색 가능하다
    
 */
public class baekjoon_11697 {

    static Queue<int[]> queue = new LinkedList<>();
    static boolean[] visited = new boolean[100001];

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        
        queue.add( new int[] {N, 0});

        int min = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int cnt = node[1], subin = node[0];
            
            if(K == subin) {
                min = Math.min(min, cnt);
                continue;
            }

            int[] nextMoves = {subin - 1, subin + 1, subin * 2};
            for (int next : nextMoves) {
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, cnt + 1});
                }
            }
                
        }

        System.out.println(min);   
    }
}