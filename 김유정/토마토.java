import java.io.*;
import java.util.*;

class 토마토{
	static int M,N,H;
	static int[][][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][N][M]; // 높이 세로 가로
		
		for(int h = 0; h < H; h++) {
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int result = bfs();
		System.out.println(result);

	}
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[] dh = {-1,1};
	private static int bfs() {
		Queue<int[]> que = new LinkedList<>();
		int isPerfect = 0;
		int day = 0;
		for(int h = 0; h < H; h++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[h][i][j]==1) que.offer(new int[] {h,i,j});
					if(map[h][i][j]==0) isPerfect++; // 모두 익어있는 상태
				}
			}
		}
		
		if(isPerfect==0) return 0;
		
		while(!que.isEmpty()) {
			int size = que.size();
			for(int s = 0; s < size; s++) {
				int[] curr = que.poll();
				int h = curr[0];
				int r = curr[1];
				int c = curr[2];
				
				for(int d = 0; d < 2; d++) { //h
					int nh = h+dh[d];
					
					if(!check(nh,r,c)) continue;
					if(map[nh][r][c]==1 || map[nh][r][c]==-1) continue;
					map[nh][r][c]=1;
					que.offer(new int[] {nh,r,c});
				}
				for(int d = 0; d < 4; d++) {//r,c
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(!check(h, nr, nc)) continue;
					if(map[h][nr][nc]==1 || map[h][nr][nc]==-1) continue;
					map[h][nr][nc]=1;
					que.offer(new int[] {h,nr,nc});
					
				}
			}
			day++;
		}
		
		isPerfect = 0;
		for(int h = 0; h < H; h++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[h][i][j]==0) isPerfect++; // 모두 익어있는 상태
				}
			}
		}
		if(isPerfect==0) return day-1;
		else return -1;
	}
	private static boolean check(int h, int r, int c) {
		return h>=0 && h<H && r>=0 && r<N && c>=0 && c<M;
	}
}