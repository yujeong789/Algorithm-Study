import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            a %= 10;
            
            if(a == 0) {
                System.out.println(10);
                continue;
            }
            
            b = b % 4 == 0 ? 4 : b % 4;
            
            int result = 1;
            for(int i = 0; i < b; i++) {
                result = (result * a) % 10;
            }
            
            System.out.println(result == 0 ? 10 : result);
        }
    }
}