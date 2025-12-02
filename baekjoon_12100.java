import java.util.*;
import java.io.*;

public class baekjoon_12100 {
   
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
   
   static int initBoard[][];
   static int N;
   
   public static void main(String[] args) throws IOException{
      N = Integer.valueOf(br.readLine());

      initBoard = new int[N][N];      
            
      for(int i=0;i<N;i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         for(int j=0;j<N;j++) {
            initBoard[i][j] = Integer.valueOf(st.nextToken());
         }   
      }
      
        System.out.println(bfs());
   }
   

   public static int bfs() {
      Queue<Step> queue = new LinkedList<>();
      queue.add(new Step(1, initBoard));
      
      int max = 0;
      while(!queue.isEmpty()) {
        
        Step thisRound = queue.poll();

        int[][] thisBoard = thisRound.board;

        int[][] slideRight = new int[N][N];         
        int[][]   slideLeft = new int[N][N];
        int[][] slideDown= new int[N][N];
        int[][] slideUp = new int[N][N];
            
            
        for(int i=0;i<N;i++) {
            slideRight[i] = Arrays.copyOf(thisRound.board[i], N);
            slideLeft[i] = Arrays.copyOf(thisRound.board[i], N);
            slideDown[i] = Arrays.copyOf(thisRound.board[i], N);
            slideUp[i] = Arrays.copyOf(thisRound.board[i], N);
        }
            

            //slide right;
        slideRight(slideRight);
        if(!Arrays.deepEquals(slideRight, thisBoard)) {
            if(thisRound.count <= 4) {
                queue.add(new Step(thisRound.count+1, slideRight));
            }
        }

        //slide left;
        slideLeft(slideLeft);
        if(!Arrays.deepEquals(slideLeft, thisBoard)) {
            if(thisRound.count <= 4) {
                
                queue.add(new Step(thisRound.count+1, slideLeft));
            }
        }
            
        //slide up
        slideUp(slideUp);
        
        if(!Arrays.deepEquals(slideUp, thisBoard)) {
            if(thisRound.count <= 4) {
                
                queue.add(new Step(thisRound.count+1, slideUp));
            }
        }
            
        //slide down;
        slideDown(slideDown);
        if(!Arrays.deepEquals(slideDown, thisBoard)) {
            if(thisRound.count <= 4) {
                
                queue.add(new Step(thisRound.count+1, slideDown));
            }
        }

        // System.out.println("-------------------" + thisRound.count + "------------------------");
        // printBoard(slideRight, thisBoard);
        // printBoard(slideLeft, thisBoard);
        // printBoard(slideDown, thisBoard);
        // printBoard(slideUp, thisBoard);

        List<Integer> maxList = new ArrayList<>();
        maxList.add(max(slideDown));
        maxList.add(max(slideUp));
        maxList.add(max(slideLeft));
        maxList.add(max(slideRight));

        int thisMax = maxList.stream().max(Integer::compareTo).orElseThrow();
        if(thisMax >= max){
            max = thisMax;
        }
         
      }
      
      return max;
      
   }

    public static int slideRight(int[][] board) {

        int max = 0;
        for(int i=0;i<N;i++) {
            int tempIndex = N-1;
            int[] tempArray = new int[N];
            for(int j=N-1;j>=0;j--) {
                if(board[i][j] != 0) {   
                    if(tempArray[tempIndex] == 0) {
                        tempArray[tempIndex] = board[i][j];
                    }
                    else if(board[i][j] == tempArray[tempIndex]) {
                        tempArray[tempIndex] = board[i][j] + tempArray[tempIndex];
                        tempIndex--;
                    }
                    else {
                        tempIndex--;
                        tempArray[tempIndex] = board[i][j];
                    }
                }
            }
            board[i] = tempArray;
        }
        return max;
    }
   
   public static int slideLeft(int[][] board) {
      
      int max = 0;
      for(int i=0;i<N;i++) {
         int tempIndex = 0;
         int[] tempArray = new int[N];
         for(int j=0;j<N;j++) {
            if(board[i][j] != 0) {
               if(tempArray[tempIndex] == 0) {
                  tempArray[tempIndex] = board[i][j];
               }
               else if(board[i][j] == tempArray[tempIndex]) {
                  tempArray[tempIndex] = board[i][j] + tempArray[tempIndex];
                  tempIndex++;
               }
               else {
                  tempIndex++;
                  tempArray[tempIndex] = board[i][j];
               }
            }
         }
         board[i] = tempArray;
      }
      return max;
   }
   
   public static int slideUp(int[][] board) {

      int max = 0;
      int[][] tempArray = new int[N][N];
      for(int i=0;i<N;i++) {
         int tempIndex = 0;
         for(int j=0;j<N;j++) {
            if(board[j][i] != 0) {
               if(tempArray[tempIndex][i] == 0) {
                  tempArray[tempIndex][i] = board[j][i];
               }
               else if(board[j][i] == tempArray[tempIndex][i]) {
                  tempArray[tempIndex][i] = board[j][i] + tempArray[tempIndex][i];
                  tempIndex++;
               }
               else if(board[j][i] != tempArray[tempIndex][i]) {
                  tempIndex++;
                  tempArray[tempIndex][i] = board[j][i];
               }
            }
         }
         //printBoard(tempArray);
         for(int j=0;j<N;j++) {
            board[j][i] = tempArray[j][i];
         }
      }
      //printBoard(board);
      return max;
   }
   
   
   public static int slideDown(int[][] board) {

      int max = 0;
      int[][] tempArray = new int[N][N];
      for(int i=0;i<N;i++) {
         int tempIndex = N-1;
         for(int j=N-1;j>=0;j--) {
            if(board[j][i] != 0) {
               if(tempArray[tempIndex][i] == 0) {
                  tempArray[tempIndex][i] = board[j][i];
               }
               else if(board[j][i] == tempArray[tempIndex][i]) {
                  tempArray[tempIndex][i] = board[j][i] + tempArray[tempIndex][i];
                  tempIndex--;
               }
               else if(board[j][i] != tempArray[tempIndex][i]) {
                  tempIndex--;
                  tempArray[tempIndex][i] = board[j][i];
               }
            }
         }
         
         for(int j=0;j<N;j++) {
            board[j][i] = tempArray[j][i];
         }
      }
      //printBoard(tempArray);
      return max;
   }
   
   
   private static int max(int[][] board){
    int max = 0;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(board[i][j] >= max){
                max = board[i][j];
            }
        }
    }
    return max;
   }
   
   
   
   public static class Step{
      int count; 
      int board[][];
      
      public Step(int count, int[][] board) {
         this.count = count;
         this.board = board;
      }
   }
   
   private static void printBoard(int[][] board, int[][] thisRound) {
      for(int i=0;i<N;i++) {
         for(int j=0;j<N;j++) {
            System.out.print(board[i][j] + " ");
         }
         System.out.print("\t");
         for(int j=0;j<N;j++){
            System.out.print(thisRound[i][j] + " ");
         }
         
         System.out.println();
      }
      
      System.out.println("--------------------------");
      
   }
   
}
