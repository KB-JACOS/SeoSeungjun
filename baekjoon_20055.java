import java.util.*;

public class Main  {
    static Queue<char[]> queue = new LinkedList<>();
    
    static int N, K;
    static int[] A;
    static boolean[] robot ;
    static int zero ;

    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        N = Integer.parseInt(st.nextToken()); 
        K = Integer.parseInt(st.nextToken());
        
        A = new int[N*2+1];
        robot = new boolean[N*2+1];

        //input
        st = new StringTokenizer(sc.nextLine());
        for(int i=1;i<=N*2;i++) A[i] = Integer.parseInt(st.nextToken());
        
    
        int cnt = 0;    // 돌아간 수, 내구도 0인 수   
        while(true) {
            cnt++; 
            // 컨베 이동
            move();

            // 로봇 이동
            robotMove();

            // 올려놓기
            if( A[1] > 0 ) {
                robot[1] = true;
                A[1] -= 1;
                if(A[1] == 0) zero++;
            }

            //로봇 이동 후 꺼내기
            if(robot[N]) robot[N] = false;
            
            if(zero >= K){
                break;
            }
        }

        System.out.println(cnt);
        
    }

    public static void move() {
        boolean cur=false, next = false;   // 한칸식 이동할때 다음 칸의 상태를 기억
        int intCur = 0, intNext = 0;

        for(int i=1;i<=N*2;i++){
            if(i == 1) {
                next = robot[i+1];
                robot[i+1] = robot[i];

                intNext = A[i+1];
                A[i+1] = A[i];
                
                continue;
            }
            
            // 2N 일 경우 
            if(i == 2*N){
                robot[1] = next;
                A[1] = intNext;
                continue;
            }

            if(i == N ){
                next = false;
                if ( robot[i]) { robot[i] = false; }
            }

            // robot 이동
            cur = robot[i+1];
            robot[i+1] = next;
            next = cur;

            // 칸 이동 (내구도)
            intCur = A[i+1];
            A[i+1] = intNext;
            intNext = intCur;
        }
    }

    public static void robotMove() {
        for(int i=N-1;i>0;i--) {
            if(robot[i] == true && robot[i+1] == false && A[i+1] > 0){
                robot[i+1] = robot[i];
                robot[i] = false;
                A[i+1] -= 1;
                if(A[i+1] == 0 ) zero++;
            }
        }
    }

}