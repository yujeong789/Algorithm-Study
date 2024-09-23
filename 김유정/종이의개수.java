package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이의개수 {
	static int N;
	static int[][] nums;
	static int minus, zero, one;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//입력
		//연산
		minus = 0; // -1인 종이의 수
		zero = 0; // 0인 종이의 수
		one = 0; // 1인 종이의 수
		dfs(0,0,N); // 시작좌표, 한변의 길이
		
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(one);
	}

	private static void dfs(int r, int c, int n) {
		boolean check = false;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(nums[r][c] != nums[r+i][c+j]) {
					check = true; // 잘 안나누어짐
					break;
				}
			}
		}
		
		if(!check) { // 잘 나누어졋으면
			if(nums[r][c]==-1) minus++;
			else if(nums[r][c]==0) zero++;
			else one++;
		}else { // 잘 안나누어 졋으면 9개로 나눈다
			dfs(r,c,n/3);
			dfs(r,c+n/3, n/3);
			dfs(r,c+2*n/3, n/3);
			
			dfs(r+n/3, c, n/3);
			dfs(r+n/3, c+n/3, n/3);
			dfs(r+n/3, c+2*n/3, n/3);
			
			dfs(r+2*n/3, c, n/3);
			dfs(r+2*n/3, c+n/3, n/3);
			dfs(r+2*n/3, c+2*n/3, n/3);
		}
		
		
	}
}
