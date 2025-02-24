import java.io.*;
import java.util.*;

public class Main {
    static boolean canInstall(int[] houses, int distance, int c) {
        int count = 1;  
        int lastPosition = houses[0];
        
        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - lastPosition >= distance) {
                count++;
                lastPosition = houses[i];
            }
        }
        
        return count >= c;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(houses);
        
        int left = 1; 
        int right = houses[n-1] - houses[0]; 
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canInstall(houses, mid, c)) {
                result = mid; 
                left = mid + 1; 
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(result);
        br.close();
    }
}