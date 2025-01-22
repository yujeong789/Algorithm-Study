import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	String site = st.nextToken();
        	String pw = st.nextToken();
        	map.put(site, pw);
        }
        for(int i = 0; i < M; i++) {
        	String site = br.readLine();
        	String pw = map.get(site);
        	System.out.println(pw);
        }
        
    }

}