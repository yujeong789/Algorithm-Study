import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// dfs로 벽 세우기
// bfs로 바이러스 퍼트리기
// 브루트포스로 안전 영역 찾기

public class 연구소 {
	
	static int N,M;
	static int[][] map;
	static int max; //안전영역의 최대 크기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		// 입력
		// 연산
		max = 0; //안전영역의 최대 크기
		dfs(0); // 벽 3개 세우기
		System.out.println(max);
	}
	
	private static void dfs(int cnt) {
		if(cnt==3) { // 벽 3개 세우기
			virus(); // 바이러스 퍼트리기
			return;
		}
		for(int i = 0; i < N; i++) { // 백트래킹
			for(int j = 0; j < M; j++) {
				if(map[i][j]==1 || map[i][j]==2) continue;
				map[i][j]=1; // 벽을 세운다
				dfs(cnt+1);
				map[i][j]=0; // 벽을 치운다
			}
		}
	}
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static void virus() { // 바이러스 퍼트리기
		Queue<int[]> que = new LinkedList<>();
		int[][] map_clone = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map_clone[i][j]=map[i][j]; //깊은복사
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j]==2) que.offer(new int[] {i,j}); // 바이러스 좌표를 큐에 담는다
			}
		}
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int r = curr[0];
			int c = curr[1];
			
			for(int d = 0; d < 4; d++) { // 사방탐색
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(map_clone[nr][nc]==1 || map_clone[nr][nc]==2) continue; // 벽이거나 바이러스면 큐에 담을 필요x
				map_clone[nr][nc]=2;
				que.offer(new int[] {nr,nc});
			}
		}
		isSafe(map_clone); // 안전 영역 크기 탐색
		
	}
	private static void isSafe(int[][] map) {
		int sum=0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j]==0) sum++;
			}
		}
		max = Math.max(sum, max); // 안전 영역 최대 크기
		return;
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
