import java.util.*;
import java.io.*;

public class codetree_택배_하차 {

    static int[][] arr;
    static int N, M;
    static List<Rect> boxList;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        boxList = new ArrayList<>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            insertBox(k, h, w, c);
        }

        // 왼쪽 기준 정렬
        Collections.sort(boxList, (o1, o2) -> Integer.compare(o1.c, o2.c));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 최대 M번 반복
        for(int i=0;i<M;i++) {

            int left = leftOut();
            int right = rightOut();

            if(left == -1 && right == -1) break;

            int target;
            if(left == -1) target = right;
            else if(right == -1) target = left;
            else target = Math.min(left, right);

            sb.append(target).append("\n");

            removeBox(target);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int leftOut() {
        Rect left = null;
        for(Rect r : boxList) {
            if(!isLeftBlank(r)) continue;

            if(left == null || r.k < left.k) left = r;
        }
        return left == null ? -1 : left.k;
    }

    public static int rightOut() {
        Rect right = null;
        for(int i = boxList.size() - 1; i >= 0; i--) {
            Rect r = boxList.get(i);

            if(!isRightBlank(r)) continue;

            if(right == null || r.k < right.k) right = r;
        }
        return right == null ? -1 : right.k;
    }

    public static boolean isLeftBlank(Rect r) {

        int ground = r.r + r.h - 1;

        for(int i=r.r;i<=ground;i++){
            for(int j=r.c-1;j>=1;j--) {
                if(arr[i][j] != 0) return false;
            }
        }

        return true;
    }

    public static boolean isRightBlank(Rect r) {

        int ground = r.r + r.h - 1;

        for(int i = r.r;i <= ground;i++){
            for(int j = r.c + r.w;j <= N;j++) {
                if(arr[i][j] != 0) return false;
            }
        }

        return true;
    }

    public static void removeBox(int k) {
        Rect target = null;

        for(Rect r : boxList) {
            if(r.k == k) {
                target = r;
                break;
            }
        }

        if(target == null) return;

        boxList.remove(target);

        // ⭐ 중력 적용
        applyGravity();
    }

    public static void applyGravity() {

    arr = new int[N+1][N+1];

    // 입력 순서 유지용 리스트
    List<Rect> temp = new ArrayList<>(boxList);

    boxList.clear();

    for(Rect r : temp) {

        int g = 1;

        while(true){
            if(g > N || !isUnderBlank(g, r.w, r.c)) {
                g--;
                break;
            }
            g++;
        }

        Rect newRect = new Rect(g - r.h + 1, r.c, r.h, r.w, r.k);
        boxList.add(newRect);

        for(int i=newRect.r;i<=g;i++) {
            for(int j=r.c;j<=r.c+r.w-1;j++) {
                arr[i][j] = r.k;
            }
        }
    }
}

    public static void insertBox(int k, int h, int w, int c) {

        int g = 1;

        while(true){
            if(g > N || !isUnderBlank(g, w, c)) {
                g--;
                break;
            }
            g++;
        }

        boxList.add(new Rect(g-h+1, c, h, w, k));

        for(int i=g-h+1;i<=g;i++) {
            for(int j=c;j<=c+w-1;j++) {
                arr[i][j] = k;
            }
        }
    }

    public static boolean isUnderBlank(int h, int w, int c) {

        int d = c + w - 1;

        for(int i=c;i<=d;i++){
            if(arr[h][i] != 0) return false;
        }

        return true;
    }

    public static class Rect {
        int r, c, h, w, k;

        public Rect(int r, int c, int h ,int w ,int k) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
        }
    }
}