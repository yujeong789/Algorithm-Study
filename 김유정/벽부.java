import java.util.*;
import java.io.*;

public class 벽부 {
	static int N,M;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				map[i][j] = tmp[j]-'0';
			}
		}
		
		int result = bfs();
		System.out.println(result);
		
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static int bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {0,0,1,0});
		boolean[][][] visited = new boolean[2][N][M];
		visited[0][0][0] = true;
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int r = curr[0];
			int c = curr[1];
			int cnt = curr[2]; //이동횟수
			int k = curr[3]; //벽부수기
			
			if(r==N-1 && c==M-1) {
				return cnt;
			}
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				
				if(map[nr][nc]==1 && k>0) continue;
				if(map[nr][nc]==1 && !visited[k+1][nr][nc]) {
					visited[k+1][nr][nc] = true;
					que.offer(new int[] {nr,nc,cnt+1,k+1});
				}else if(map[nr][nc]==0 && !visited[k][nr][nc]){
					visited[k][nr][nc] = true;
					que.offer(new int[] {nr,nc,cnt+1,k});
				}
			}
		}
		return -1;
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
