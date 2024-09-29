import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로1 {
	static int T, N;
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t <= 10; t++) {
			T = Integer.parseInt(br.readLine());
			N=16;
			map = new char[N][N];
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			boolean result = false;
			findway:
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j]=='2') {
						visited[i][j]=true;
						result = bfs(i,j);
						break findway;
					}
				}
			}
			System.out.println("#"+T+" " +(result?1:0));
		}
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static boolean bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr,sc});
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int r = curr[0];
			int c = curr[1];
			if(map[r][c]=='3') {
				return true;
			}
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(map[nr][nc]=='1') continue;
				if(visited[nr][nc]) continue;
				visited[nr][nc] = true;
				que.offer(new int[] {nr,nc});
			}
			
		}
		return false;
		
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
