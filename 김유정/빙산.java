import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산 {
	static int N,M;
	static int[][] map;
	static int min;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time=0;
		
		while(true) {
			// 빙하 녹이기
			removeBingsan(map);
			
			time++;
//			for(int k = 0; k < N; k++) {
//				System.out.println(Arrays.toString(map[k]));
//			}System.out.println();

			// 빙하 개수 세기
			int cnt=0;
			visited = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j]>0 && !visited[i][j]) {
						visited[i][j] = true;
						cntBingsan(i,j);
						cnt++;
//						System.out.println("cnt:" +cnt);
						
//						for(int k = 0; k < N; k++) {
//							System.out.println(Arrays.toString(visited[k]));
//						}System.out.println();
					}
				}
			}
			
			if(cnt==0) {
				System.out.println(0);
				break;
			}
			if(cnt>1) {
				System.out.println(time);
				break;
			}
			
//			System.out.println(time);
		}
		
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static void cntBingsan(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {r,c});
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			r = curr[0];
			c = curr[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(map[nr][nc]==0) continue;
				if(visited[nr][nc]) continue;
				visited[nr][nc]=true;
				que.offer(new int[] {nr,nc});
			}
		}
	}

	private static void removeBingsan(int[][] map) {
		// 바다와 접한 면 찾기
		int[][] tmp = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] > 0) { // 빙산이
					int sea = 0;
					for(int d = 0; d < 4; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						if(check(nr,nc) && map[nr][nc]==0) { // 물과 접해있으면
							sea++;
						}
					}
					tmp[i][j] = sea;
				}
			}
		}

		// 빙산 녹이기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] > 0) { // 빙산이
					if(map[i][j]-tmp[i][j]<0) map[i][j]=0;
					else map[i][j] -= tmp[i][j];
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
