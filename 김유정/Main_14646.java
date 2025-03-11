import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] roll = new int[N+1];
        int ans = 0;
        int max = 0;
        for(int i = 0; i < 2*N; i++) {
        	int n = Integer.parseInt(st.nextToken());
        	if(roll[n]==0) {
        		max = Math.max(++ans, max);
        		roll[n]=1;
        	}else {
        		roll[n]=-1;
        		ans--;
        	}
        }
        System.out.println(max);
        
    }
}
