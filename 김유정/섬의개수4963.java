package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 섬의개수4963 {
	static int W,H; // 너비, 높이 <=50
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,-1,0,1,1,1,0,-1}; // 12방향부터 CW
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int num;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		num=0;
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			if(W==0 && H==0) break; // 종료조건
			
			map = new int[H][W];
			visited = new boolean[H][W];
			int island = 0;
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			// 입력----------------
			// 연산----------------
			for(int i = 0; i < H; i++) { // map을 한칸씩 돌아보기
				for(int j = 0; j < W; j++) {
					if(map[i][j]==1 && !visited[i][j]) { // 섬이 있는데 방문한 적이 없으면
						island++; // 섬개수+1
						bfs(i,j); // 해당 위치에 대해 주변을 탐색한다
							      // -> 주변에 섬이 있으면 방문한적이 있다고 visited 배열에 넣음
						          // -> 연결된 1에 대해 섬개수 추가 방지)
					}
				}
			} // for
			System.out.println(island);
		} // while
	}

	static void bfs(int sr, int sc) {
		// 조건에 해당하는 경우 전부 큐에 삽입
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr,sc});
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll(); // 가장 앞의 값을 빼서 반복문을 돌린다
			int r = cur[0];
			int c = cur[1];

			//-------------------
			for(int d = 0; d < 8; d++) { // 팔방 탐색
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue; //범위를 벗어날 때
				if(visited[nr][nc]) continue; //방문했을 때

				if(map[nr][nc]==1) { // 1이면 방문했다고 체크한다.
					visited[nr][nc]=true; // 팔방탐색할 때 true로 체크해야 나중에 섬개수를 중복 +1하는걸 방지 가능
					que.offer(new int[] {nr,nc}); // 그리고 que에 넣기(그자리에서 다시 팔방탐색해야하니까)
				}
			}
		}
	}

	// 범위 안에 있는지 체크하는 로직
	private static boolean check(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W; //범위안에 있으면 true
	}
}
