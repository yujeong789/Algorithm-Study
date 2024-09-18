package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자끼워넣기 {
	static int N;
	static int[] num;
	static int A,B,C,D;
	static int max, min;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		A=Integer.parseInt(st.nextToken());
		B=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		
		max = Integer.MIN_VALUE; 
		min = Integer.MAX_VALUE;
		result = num[0];
		
		dfs(1, A, B, C, D, result);
		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int cnt, int plus, int minus, int multiple, int divide, int result) {
		if(cnt==N) {
			max = Math.max(result, max);
			min = Math.min(result, min);
			return;
		}

		if(plus-1>=0) {
			dfs(cnt+1, plus-1, minus, multiple, divide, result+num[cnt]);
		}
		if(minus-1>=0) {
			dfs(cnt+1, plus, minus-1, multiple, divide, result-num[cnt]);
		}
		if(multiple-1>=0) {
			dfs(cnt+1, plus, minus, multiple-1, divide, result*num[cnt]);
		}
		if(divide-1>=0) {
			dfs(cnt+1, plus, minus, multiple, divide-1, result/num[cnt]);
		}
		
	}
}
