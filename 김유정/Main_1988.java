import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Map<Integer, Integer> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        
        int ans = fibo(N);
        if(ans>0) System.out.println(1);
        else if(ans==0) System.out.println(0);
        else System.out.println(-1);
        System.out.println(Math.abs(ans));
    }
    
	private static int fibo(int n) {
		if(map.containsKey(n)) return map.get(n);
		
		int fibo = 0;
		if(n>1) {
			fibo = fibo(n-1)+fibo(n-2);
		}else{
			fibo = fibo(n+2)-fibo(n+1);
		}
		map.put(n, fibo%1000000000);
		return map.get(n);
	}

	
}
