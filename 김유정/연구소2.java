import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소2 {
	static int N,M;
	static int[][] map;
	static int min;
	
	public static void main(String[] args) throws IOException {
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
		}
		// 입력

		List<int[]> virus_pos = new ArrayList<>();
		List<int[]> select_pos = new ArrayList<>();
		// 바이러스 놓을 수 있는 위치 탐색
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j]==2) virus_pos.add(new int[] {i,j});
			}
		}
//		for(int i = 0; i < virus_pos.size(); i++) {
//			System.out.print(Arrays.toString(virus_pos.get(i)) + " ");
//		}System.out.println();
		
		// 바이러스의 위치 선택
		min = Integer.MAX_VALUE;
		combination(0, 0, virus_pos, select_pos);
		if(min==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}

	
	// 바이러스의 위치 선택
	private static void combination(int cnt, int lastIdx, List<int[]> virus_pos, List<int[]> select_pos) {
		if(cnt==M) {
			// 선택한 바이러스 위치 출력해서 보기
//			for(int i = 0; i < M; i++) {
//				System.out.print(Arrays.toString(select_pos.get(i)) + " ");
//			}System.out.println();
			
			bfs(select_pos);
			return;
		}
		
		for(int i = lastIdx; i < virus_pos.size(); i++) {
			if(select_pos.contains(virus_pos.get(i))) continue;
			
			select_pos.add(virus_pos.get(i));
			combination(cnt+1, i+1, virus_pos, select_pos);
			select_pos.remove(select_pos.size()-1);
		}
	}


	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	// 바이러스 퍼트리기
	private static void bfs(List<int[]> select_pos) {
		// 배열의 깊은 복사
		int[][] virus = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j]==1) virus[i][j] = -1; // 벽 위치는 -1
			}
		}
		
		Queue<int[]> que = new LinkedList<>();
		for(int[] pos: select_pos) {
			virus[pos[0]][pos[1]] = 1; // 0부터 시작해야하는데, 0의 숫자를 세고 싶으므로 1부터 움직인다
			que.offer(new int[] {pos[0], pos[1], 2}); // 현재 시간을 배열에 넣는다.
		}
		
		int ans = 0; // 시간
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int r = curr[0];
			int c = curr[1];
			int time = curr[2];
			
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(virus[nr][nc]==-1) continue; //벽이면 건너뛴다
				if(virus[nr][nc] > 0) continue; //이미 값이 들어있으면 건너뛴다
				virus[nr][nc]=time;
				que.offer(new int[] {nr,nc,time+1});
			}
			ans = time-1;
		}
//		System.out.println("ans: " +ans);
		boolean isZero = checkVirus(virus);
		if(isZero) return;
		else min = Math.min(min, ans-1);
		return;
	}


	private static boolean checkVirus(int[][] virus) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(virus[i][j]==0) return true; //0이 있으면 true반환
			}
		}
		return false; //0이 없다
	}


	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
