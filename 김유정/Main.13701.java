import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        BitSet seen = new BitSet(33554432);
        
        boolean isFirst = true;
        
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            
            if (!seen.get(num)) {
                if (!isFirst) {
                    sb.append(" ");
                }
                isFirst = false;
                
                sb.append(num);
                
                seen.set(num);
            }
        }
        
        System.out.println(sb.toString());
    }
}
