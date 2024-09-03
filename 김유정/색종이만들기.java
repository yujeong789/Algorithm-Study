package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이만들기 {
	static int N;
	static int[][] map;
	static int white, blue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		white = 0;
		blue = 0;
		countPaper(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	static void countPaper(int r, int c, int N) {
		boolean check = true;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++ ) {
				if(map[r][c]!=map[r+i][c+j]) {
					check=false;
					break;
				}
			}
		}
		
		if(check) {
			System.out.println(r + " " + c);
			if(map[r][c]==0) white++;
			else blue++;
		}else {
			countPaper(r,c,N/2);
			countPaper(r+N/2, c, N/2);
			countPaper(r,c+N/2,N/2);
			countPaper(r+N/2, c+N/2, N/2);
			
		}
	}
}
