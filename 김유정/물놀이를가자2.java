package algorithm0904;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물놀이를가자2 {
	static int T;
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int min;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			for(int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			result = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {					
					if(map[i][j]=='L') {
						visited = new boolean[N][M];
						bfs(i,j);
					}
				}
			}
			System.out.println(result);
			
		}
	}
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static void bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr,sc});
		int cnt=0;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0];
			int c = cur[1];
			if(map[r][c]=='W') return; //물에 도착했으면 멈춰라
			visited[r][c] = true;
			cnt++;
			
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(!check(nr,nc)) continue; // 범위벗어나면
				if(visited[nr][nc]) continue; //방문한적 있으면
				if(map[nr][nc]=='L') {
					que.offer(new int[] {nr,nc});
				}else {
					for(int i = 0; i < N; i++) {
						System.out.println(Arrays.toString(visited[i]));
					}
					System.out.println(cnt);
					result += cnt;
					return;
				}
			}
		}
		
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
