package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 곱셈 {
	static int A,B,C;
	static long num;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		num = dfs(A,B,C);
		System.out.println(num);
	}

	private static long dfs(int a, int b, int c) {
		
		if(b==1) return a%c;
		
		long tmp = dfs(a, b/2, c); // 분할정복
		if(b%2==1) {
			return (tmp*tmp)%c * a % c;
//			return tmp*tmp*a%c; // 오버플로우로 인해 long 형 범위를 넘어 쓰레기값이 나온다
		}else {
			return tmp*tmp%c;
		}
	}
}
