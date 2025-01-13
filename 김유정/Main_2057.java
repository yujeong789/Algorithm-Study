import java.util.*;
import java.io.*;

public class Main {
	static long N;
	static long[] fact;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		if(N == 0) {
            System.out.println("NO");
            return;
        }
		
		fact = new long[21];
		fact[0] = 1;
		for(int i = 1; i < 21; i++) {
			fact[i] = fact[i-1] * i;
		}
		
		for(int i = 20; i >= 0; i--) {
			if(fact[i]<=N && N>0) N-=fact[i];
			if(N==0) break;
		}
		System.out.println(N==0?"YES" : "NO");
	}
}
