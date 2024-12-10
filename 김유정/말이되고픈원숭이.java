import java.io.*;
import java.util.*;

public class 말이되고픈원숭이 {
	static int K;
	static int W,H;
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K+1];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력
		//연산
		int result = bfs(0,0);
		System.out.println(result);
	}
	
	static int[] horsex = {-2,-1,1,2,2,1,-1,-2}; //팔방
	static int[] horsey = {1,2,2,1,-1,-2,-2,-1};
	static int[] monkeyx = {-1,0,1,0}; //사방
	static int[] monkeyy = {0,1,0,-1};
	
	//bfs 주변 탐색
	private static int bfs(int sx, int sy) {
		Queue<int[]> que = new LinkedList<>();
		
		// 처음 x, 처음 y, k(말 이동 횟수), 거리
		que.offer(new int[] {sx,sy,K,0});			
		visited[0][0][K] = true;
		
		while(!que.isEmpty()) {
			int x = que.peek()[0];
			int y = que.peek()[1];
			int k = que.peek()[2];
			int dist = que.peek()[3];
			que.poll();
			
//			System.out.println(que.size());
			
			if(x==H-1 && y==W-1) {
				return dist;
			} // 종점까지 가면 거리 리턴
			
			//원숭이 사방탐색
			for(int d = 0; d < 4; d++) {
				int nx = x+monkeyx[d];
				int ny = y+monkeyy[d];
				
				if(!check(nx,ny)) continue; //범위체크
				if(map[nx][ny]==1) continue; //장애물때문에 못가
				if(visited[nx][ny][k]) continue; //방문한적 잇니?
				visited[nx][ny][k] = true;
				que.offer(new int[] {nx,ny,k, dist+1});
			}
			
			if(k==0) continue; // 만약 k가 남은 횟수가 없다면 아래로 내려가지x
			
			// 말 팔방탐색
			for(int d = 0; d < 8; d++) {
				int nx = x+horsex[d];
				int ny = y+horsey[d];
				
				if(!check(nx,ny)) continue; //범위체크
				if(map[nx][ny]==1) continue; //장애물때문에 못가
				if(visited[nx][ny][k-1]) continue; //방문한적 잇니?
				visited[nx][ny][k-1] = true;
				que.offer(new int[] {nx,ny,k-1, dist+1});
			}
			
		}
		return -1; //장애물때문에 종점까지 못가면 -1리턴
	}
	
	private static boolean check(int x, int y) {
		return x>=0 && x<H && y>=0 && y<W;
	}

}
