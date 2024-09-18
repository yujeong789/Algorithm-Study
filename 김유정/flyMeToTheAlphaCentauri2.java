package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class flyMeToTheAlphaCentauri2 {
	static int T;
	static long x,y;
	static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken()); 
			
			count(x,y);
		}
	}

	private static void count(long start, long end) {
		long dif = end-start; //거리차
		long root = (long)Math.sqrt(dif);//거리 차의 제곱근
		
		if(root*root==dif) {
			System.out.println(2*root-1);
		}else if(root*root+root >=dif) {
			System.out.println(2*root);
		}else {
			System.out.println(2*root+1);
		}
		
	}
	
}

/*
규칙
y-x	root	cnt
1	1 (1)	1
2	1 (2)	2
3	1		3
4	2 (1)	3
5	2 (2)	4
6	2 (2)	4
7	2		5
8	2		5
9	3 (1)	5
10	3 (2)	6

*/
