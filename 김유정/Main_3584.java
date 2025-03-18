import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); 
        
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1]; 
            
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); 
                int b = Integer.parseInt(st.nextToken()); 
                parent[b] = a; 
            }
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            Set<Integer> ancestors = new HashSet<>();
            
            while (u != 0) {
                ancestors.add(u);
                u = parent[u];
            }
            
            while (v != 0) {
                if (ancestors.contains(v)) {
                    System.out.println(v); 
                    break;
                }
                v = parent[v];
            }
        }
    }
}
