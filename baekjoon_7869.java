import java.util.*;
import java.io.*;

public class baekjoon_7869 {


    private static int CROSS = 0; 
    private static int NOT_CROSS = 1; 
    private static int INNER = 2; 
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Double x1, x2, y1, y2, r1, r2; 

        


        x1 = Double.parseDouble(st.nextToken()); 
        y1 = Double.parseDouble(st.nextToken()); 
        r1 = Double.parseDouble(st.nextToken());

        x2 = Double.parseDouble(st.nextToken()); 
        y2 = Double.parseDouble(st.nextToken()); 
        r2 = Double.parseDouble(st.nextToken());

        

        Double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        if(r1 + r2 <= dist){
            System.out.println("0.000");
        }

        else if(dist <= (Math.max(r1, r2) - Math.min(r1, r2))) {
            System.out.printf("%.3f", (Math.pow(Math.min(r1, r2), 2) * Math.PI));
        }

        else{
            Double cosTheta1 = (Math.pow(r1, 2) + Math.pow(dist, 2) - Math.pow(r2, 2)) / (2.0 * r1 * dist);
            Double cosTheta2 = (Math.pow(r2, 2) + Math.pow(dist, 2) - Math.pow(r1, 2)) / (2.0 * r2 * dist);

            Double theta1 = Math.acos(cosTheta1) * 2.0;
            Double theta2 = Math.acos(cosTheta2) * 2.0;

            Double sumOfSector = (Math.pow(r1, 2) * theta1)/2.0 + (Math.pow(r2, 2) * theta2)/2.0;
            Double sumOfTri = (Math.pow(r1, 2) * Math.sin(theta1)) / 2.0 + (Math.pow(r2, 2) * Math.sin(theta2)) / 2.0;

            System.out.printf("%.3f", sumOfSector - sumOfTri);
        }
        
    }

}
