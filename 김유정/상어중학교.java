import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상어중학교 {
	
	static int N,M;
	static int[][] map;
	static int point;
	static boolean[][] visited;
	static Queue<int[]> block_group;
	
	public static void main(String[] args) throws IOException {
		//검정-1, 무지개0, 색은1,2,...,M자연수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력
	
		point = 0; //점수
		play();
		System.out.println(point);
		
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}System.out.println();
	}

	private static void play() {
		boolean isPlay = true;
		while(true) {
			// 1. 블록그룹 찾기
			block_group = new LinkedList<>();
			find_group();
			if(block_group.size()==0) return;
			int[] group = decide_group(block_group);
			
			// 2. 블록그룹 제거, 점수 획득(없어진블록개수의제곱만큼)
			int cnt = remove_group(group);
			point += cnt*cnt;
//			System.out.println("사라진 블록 : "+cnt);
//			System.out.println("블록그룹제거 -> -20");
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}System.out.println();
			
			// 3. 중력 작용
			gravity();
//			System.out.println("중력작용");
//			for(int k = 0; k < N; k++) {
//				System.out.println(Arrays.toString(map[k]));
//			}System.out.println();
			
			// 4. 90도 반시계방향으로 회전
			rotation();
//			System.out.println("90도 회전");
//			for(int k = 0; k < N; k++) {
//				System.out.println(Arrays.toString(map[k]));
//			}System.out.println();
//			
			// 5. 중력 작용
			gravity();
//			System.out.println("중력작용");
//			for(int k = 0; k < N; k++) {
//				System.out.println(Arrays.toString(map[k]));
//			}System.out.println();
		}

		
	}

	private static int[] decide_group(Queue<int[]> block_group) {
		if(block_group.size()==1) {
			return new int[] {block_group.peek()[0], block_group.peek()[1]};
		}
		
		// 무지개 블록이 가장 많은 블록그룹 찾기
		Queue<int[]> rainbow = new LinkedList<>();
		rainbow.offer(new int[] {block_group.peek()[0], block_group.peek()[1], block_group.peek()[2]});
		block_group.poll();
		int size = block_group.size();
		for(int i = 0; i < size; i++) {
			int[] curr = block_group.poll();
			if(rainbow.peek()[2] == curr[2]) rainbow.offer(new int[] {curr[0], curr[1], curr[2]});
			else if(rainbow.peek()[2] < curr[2]) {
				rainbow = new LinkedList<>();
				rainbow.offer(new int[] {curr[0], curr[1], curr[2]});
			}
		}
		if(rainbow.size()==1) {
			return new int[] {rainbow.peek()[0], rainbow.peek()[1]};
		}
		
		//가장큰 행 찾기
		Queue<int[]> row = new LinkedList<>();
		row.offer(new int[] {rainbow.peek()[0], rainbow.peek()[1]});
		rainbow.poll();

		size = rainbow.size();
		for(int i = 0; i < size; i++) {
			int[] curr = rainbow.poll();
			if(row.peek()[0] == curr[0]) row.offer(new int[] {curr[0], curr[1]});
			else if(row.peek()[0] < curr[0]) {
				row = new LinkedList<>();
				row.offer(new int[] {curr[0], curr[1]});
			}
		}
		if(row.size()==1) {
//			System.out.println("결정: "+Arrays.toString(row.peek()));
			return row.peek();
		}
		
		// 가장 큰 열 찾기
		Queue<int[]> column = new LinkedList<>();
		column.offer(new int[] {row.peek()[0], row.peek()[1]});

		size = row.size();
		for(int i = 0; i < size; i++) {
			int[] curr = row.poll();
			if(column.peek()[1] == curr[1]) row.offer(new int[] {curr[0], curr[1]});
			else if(row.peek()[1] < curr[1]) {
				column = new LinkedList<>();
				column.offer(new int[] {curr[0], curr[1]});
			}
		}
//		System.out.println("결정: "+Arrays.toString(column.peek()));
		return column.peek();
	}

	// 4. 90도 반시계방향으로 회전
	private static void rotation() {
		int[][] tmp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				tmp[N-1-j][i] = map[i][j];
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = tmp[i][j];
			}
		}
		
	}

	// 3. 중력 작용 / 5. 중력 작용
	private static void gravity() {
		for(int j = 0; j < N; j++) {
			for(int i = N-2; i >= 0; i--) {
				if(map[i][j]==-1) continue;
				int idx = i;
				while(true) {
					if(idx==N-1) break;
					if(map[idx+1][j]>=-1) break;		
					map[idx+1][j] = map[idx][j]; // 아래와 swqp
					map[idx][j] = -20; // 아래와 swqp
					idx++;
				}
			}
		}
		
	}

	// 2. 블록그룹 제거, 점수 획득(없어진블록개수의제곱만큼)
	private static int remove_group(int[] block_group) {
		int color = map[block_group[0]][block_group[1]];
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {block_group[0], block_group[1]});
		
		int cnt = 0; //사라진 블록의 개수
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int r = curr[0];
			int c = curr[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(map[nr][nc]==-1) continue; //검은색
				if(map[nr][nc]>0 && map[nr][nc]!=color) continue; // 일반블록 색이 다르면
				if(map[nr][nc]==-20) continue;
				
				map[nr][nc]=-20; //삭제
				cnt++;
				que.offer(new int[] {nr,nc});
			}
		}
		return cnt;
	}

	// 1. 블록그룹 찾기
	private static void find_group() {
		int[] tmp = new int[2];
		visited = new boolean[N][N];
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j]>0 && !visited[i][j]) { //일반 색 블록
					visited[i][j]=true;
					int cnt = bfs(i,j); // 1-1. flood fill
					int rainbow = rainbowBfs(i,j);
					if(max == cnt && cnt>=2) {
						block_group.offer(new int[] {i,j,rainbow});
					} else if(max < cnt) { // 가장 큰 블록 반환
						max = cnt;
						block_group = new LinkedList<>();
						block_group.offer(new int[] {i,j,rainbow});
					}
				}
			}
		}
//		for(int[] t : block_group) {
//			System.out.println(Arrays.toString(t));
//		}System.out.println();
	}

	// 1-1. flood fill
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static int bfs(int sr, int sc) {
		int[][] group = new int[N][N];
		group[sr][sc]=1;
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr,sc});
		int cnt=0;
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int r = curr[0];
			int c = curr[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(map[nr][nc]==-1) continue; //검은색
				if(map[nr][nc]==-20) continue; //삭제된곳
				if(group[nr][nc]==1) continue; //이미방문한곳
				if(map[nr][nc]>0 && map[nr][nc]!=map[sr][sc]) continue; // 일반블록 색이 다르면
				
				if((map[nr][nc]>0 && map[nr][nc]==map[sr][sc])||map[nr][nc]==0) visited[nr][nc]=true;
				group[nr][nc]=1;
				que.offer(new int[] {nr,nc});
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(group[i][j]==1) cnt++;
			}
		}
		if(cnt<2) return 0;
		return cnt;
	}
	
	private static int rainbowBfs(int sr, int sc) {
		int[][] group = new int[N][N];
		group[sr][sc]=1;
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr,sc});
		int cnt=0;
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int r = curr[0];
			int c = curr[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(map[nr][nc]==-1) continue; //검은색
				if(map[nr][nc]==-20) continue; //삭제된곳
				if(group[nr][nc]==1) continue; //이미방문한곳
				if(map[nr][nc]>0 && map[nr][nc]!=map[sr][sc]) continue; // 일반블록 색이 다르면
				
				if((map[nr][nc]>0 && map[nr][nc]==map[sr][sc])||map[nr][nc]==0) visited[nr][nc]=true;
				group[nr][nc]=1;
				que.offer(new int[] {nr,nc});
			}
		}
		//무지개블록 개수
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(group[i][j]==1 && map[i][j]==0) cnt++;
			}
		}
		return cnt;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
