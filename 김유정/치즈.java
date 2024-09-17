import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈 {
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int result;
	static Queue<int[]> que;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
//		int one = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				if(map[i][j]==1) one++;
			}
		}
		//입력---------
		//연산---------
		result = 0;
		while(true) {
			int one = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j]==1) {
						one++; //치즈개수 카운트
					}
				}
			}
			if(one==0) break; //치즈가 없으면 중지
			visited = new int [N][M]; //방문배열초기화
			checkCheese(0,0); //bfs
			
			for(int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();
			for(int i = 0; i < N; i ++) {
				for(int j = 0; j < M; j++) { //공기에 2번이상 닿은 치즈 삭제
					if(visited[i][j]>=2) map[i][j]=0;
				}
			}
			result++;

		}//while
		
		System.out.println(result);
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static void checkCheese(int r, int c) {
		que = new LinkedList<>();
		que.offer(new int[] {r,c});
		
		while(!que.isEmpty()) {
			r = que.peek()[0];
			c = que.peek()[1];
			que.poll();
//			System.out.println(r+" "+c);
			
			for(int d = 0; d < 4; d++) { //사방탐색
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(!check(nr,nc)) continue;
				if(visited[nr][nc]==-1) continue;
				if(map[nr][nc]==0) { //0이면 큐에 추가
					que.offer(new int[] {nr,nc});
					visited[nr][nc]=-1;
				}else { //1이면 방문배열에 체크
					visited[nr][nc]+=1;
				}
			}
		}
		
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
