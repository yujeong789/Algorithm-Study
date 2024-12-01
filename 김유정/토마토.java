import java.awt.BufferCapabilities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토 {
	static int M,N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = bfs();
		System.out.println(result);
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static int bfs() {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int day = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j]==1) { //익은토마토
					que.offer(new int[] {i,j});
					visited[i][j] = true;
				}
			}
		}		
		
		while(!que.isEmpty()) {
			int size = que.size();
			for(int s = 0; s < size; s++) {
				int[] curr = que.poll();
				int r = curr[0];
				int c = curr[1];
				
				for(int d = 0; d < 4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(!check(nr,nc)) continue;
					if(map[nr][nc]==1 || map[nr][nc]==-1) continue;
					if(visited[nr][nc]) continue;
					visited[nr][nc] = true;
					map[nr][nc]=1;
					que.offer(new int[] {nr,nc});
				}
			}
			day++;
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		for(int i =0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j]==0) return -1;
			}
		}
		return day-1;
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
