import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 등산로조성 {
	static int T, N, K;
	static int[][] map;
	static int max;
	
	 public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}//입력
			
			// 가장 높은 봉우리 찾기
			int height = 0;
			Queue<int[]> highest = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j]==height) {
						highest.offer(new int[] {i,j});
					}
					if(map[i][j] > height) {
						height = map[i][j];
						highest = new LinkedList<>();
						highest.offer(new int[] {i,j});
					}
				}
			}
			
			max = 0;
			boolean[][] visited = new boolean[N][N];
			int size = highest.size();
			for(int i = 0; i < size; i++) {
				visited[highest.peek()[0]][highest.peek()[1]]=true;
				find_way(highest.peek()[0], highest.peek()[1], K, 1, visited);
				visited[highest.peek()[0]][highest.peek()[1]]=false;
				highest.poll();
			}
			System.out.println("#"+t+" "+max);
		}
	}
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static void find_way(int r, int c, int k, int cnt, boolean[][] visited) {
		
		boolean isPossible = false;
		for(int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!check(nr,nc)) continue;
			if(visited[nr][nc]) continue;
			if(k>0) {
				for(int i = 1; i <= K; i++) {
					if(map[r][c]>map[nr][nc]-i) {
						map[nr][nc] -= i;
//						for(int ll = 0; ll < N; ll++) {
//							System.out.println(Arrays.toString(map[ll]));
//						}System.out.println();
						visited[nr][nc]=true;
						find_way(nr,nc,0, cnt+1,visited);
						visited[nr][nc]=false;
						isPossible = true;
						map[nr][nc] += i;
					}
				}
			}
			if(map[r][c]>map[nr][nc]) {
				visited[nr][nc]=true;
				find_way(nr,nc,k,cnt+1,visited);
				visited[nr][nc]=false;
				isPossible = true;
			}
		}
		if(!isPossible) max = Math.max(max, cnt);
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
