import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon_15683 {
    
    //상 우 하 좌 
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static int N, M, camCnt, result = Integer.MAX_VALUE;    
    static final int LOOK = 7;
    static List<int[]> camList = new ArrayList<>();
    int[] c1 = {0, 1, 2, 3};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] <= 5 && arr[i][j] >= 1) {
                    camList.add(new int[]{i, j});
                }
            }
        }

        dfs(arr, 0);
        System.out.println(result);
    }

    public static void dfs(int[][] arr, int camIdx)  {
        if(camIdx == camList.size()) {
            int notLookCnt = countNotLook(arr);

            result = Math.min(notLookCnt, result);
            return;
        }

        int[] curCam =  camList.get(camIdx);
        int cr = curCam[0];
        int cc = curCam[1];
        int cn = arr[cr][cc];

        for(int i=0;i<4;i++) {
            int[][] curArr = copy(arr);

            if(cn == 1) cam1(cr, cc, i, curArr);
            else if(cn == 2) cam2(cr, cc, i, curArr);
            else if(cn == 3) cam3(cr, cc, i, curArr);
            else if(cn == 4) cam4(cr, cc, i, curArr);
            else if(cn == 5) cam5(cr, cc, i, curArr);

            dfs(curArr, camIdx+1);
        }
    }


    public static int countNotLook(int[][] arr) {

        int sum = 0;
        for(int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if(arr[i][j] == 0) sum++;
            }
        }

        return sum;
    }
    

    public static int[][] copy (int[][] arr) {
        int[][] copy = new int[N][M];
        for(int i=0;i<N;i++) {
            copy[i] = Arrays.copyOf(arr[i], M);
        }

        return copy;
    }

    public static void cam5(int r, int c, int toward, int[][] arr) {
        for(int i=0;i<4;i++) {
            goFoward(r, c, i, arr);
        }
    }

    public static void cam4(int r, int c, int toward, int[][] arr){
        goFoward(r, c, toward, arr);
        goFoward(r, c, (toward - 1 + 4) % 4, arr);
        goFoward(r, c, (toward - 2 + 4) % 4, arr);
    }

    public static void cam3(int r, int c, int toward, int[][] arr){
        goFoward(r, c, toward, arr);
        goFoward(r, c, (toward - 1 + 4) % 4, arr);
    }

    public static void cam2(int r, int c, int toward, int[][] arr){
        goFoward(r, c, toward, arr);
        goFoward(r, c, (toward + 2) % 4, arr);
    }

    public static void cam1(int r, int c, int toward, int[][] arr) {
        goFoward(r, c, toward, arr);
    }

    public static void goFoward(int r, int c, int toward, int[][] arr) {        
        
        while(true) {
            int nr = r + dr[toward];
            int nc = c + dc[toward];

            if(nr < 0 || nc < 0 || nr >= N || nc >= M) {
                break;
            }

            if(arr[nr][nc] == 6) { break;}

            if(arr[nr][nc] == 0) { arr[nr][nc] = LOOK; }
            
            r = nr;
            c = nc;
        }
    }
}