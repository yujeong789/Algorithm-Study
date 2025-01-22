import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Long> decreasing = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i <= 9; i++) {
            makeDecreasing(i, 1);
        }
        
        Collections.sort(decreasing);
        
        if (N >= decreasing.size()) {
            System.out.println(-1);
        } else {
            System.out.println(decreasing.get(N));
        }
    }
    
    static void makeDecreasing(long num, int len) {
        decreasing.add(num);
        
        if (len > 10) return;
        
        for (int i = 0; i < num % 10; i++) {
            makeDecreasing(num * 10 + i, len + 1);
        }
    }
}