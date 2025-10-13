import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] box = new int[N][M];

        int unriped = 0;
        int totalCnt = 0;

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                int status = Integer.parseInt(st.nextToken());
                box[i][j] = status;                             // 박스에 상태 저장
                if(status != -1) {                              // 토마토 개수 세기
                    totalCnt++;
                    if(status == 0)
                        unriped++;                              // 안익은 토마토 개수 세기
                    else
                        queue.add(new int[] {i, j, 0});         // 익은 토마토는 큐에 넣기
                }
            }
        }

        int riped = totalCnt - unriped;
        int days = 0;

        int[] dr = {0, 0, -1, 1}, dc = {1, -1, 0, 0};
        int changeCnt = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            days = cur[2];

            for(int i=0;i<4;i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

                if(box[nr][nc] == 0) {
                    box[nr][nc] = 1;                            // 토마토 익히기
                    queue.add(new int[]{ nr, nc, days + 1 });
                    changeCnt++;                                // 바뀐 토마토 세기
                }
            }
        }

        if(changeCnt + riped == totalCnt) {
            System.out.println(days);
        }

        else {
            System.out.println(-1);
        }
    }
}
