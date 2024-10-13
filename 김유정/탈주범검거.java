import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 탈주범검거 {
	static int T;
	static int N,M,R,C,L; //터널세로, 터널가로, 맨홀세로, 맨홀가로, 탈출후소요시간
	static int[][] map;
	static Set<String> result;
	static Map<Integer, int[]> move;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());//출발
			C = Integer.parseInt(st.nextToken());//출발
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			result = new HashSet<>();
			move = new HashMap<>();
			move.put(1, new int[] {0,1,2,3}); //상하좌우
			move.put(2, new int[] {0,2}); //상하
			move.put(3, new int[] {1,3}); //좌우
			move.put(4, new int[] {0,1}); //상우
			move.put(5, new int[] {1,2}); //하우
			move.put(6, new int[] {2,3}); //하좌
			move.put(7, new int[] {0,3}); //상좌
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			System.out.println(Arrays.toString(move.get(map[R][C])));
			boolean[][] visited = new boolean[N][M];
			visited[R][C]=true;
			exit(0,R,C,visited);
			int size = result.size();
//			for(int[] i : result) {
//				System.out.println(Arrays.toString(i));
//				result.poll();
//			}
			System.out.println("#"+t+" "+result.size());
		}
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static void exit(int time, int r, int c, boolean[][] visited) {
		if(time==L) {
			return;
		}
//		if(result.contains(r+" "+c)) {
//			return;
//		}
		result.add(r+" "+c);	
		
		
		int[] direction = move.get(map[r][c]);
//		System.out.println(Arrays.toString(direction));
		
		for(int d = 0; d < direction.length; d++) {
			int nr = r+dr[direction[d]];
			int nc = c+dc[direction[d]];
			
			if(!check(nr,nc)) continue;
			if(visited[nr][nc]) continue;
			if(map[nr][nc]==0) continue;
			
			if(direction[d]==0)
				if(map[nr][nc]==3 ||map[nr][nc]==4 ||map[nr][nc]==7) continue;
			if(direction[d]==1)
				if(map[nr][nc]==2 ||map[nr][nc]==4 ||map[nr][nc]==5) continue;
			if(direction[d]==2)
				if(map[nr][nc]==3 ||map[nr][nc]==5 ||map[nr][nc]==6) continue;
			if(direction[d]==3)
				if(map[nr][nc]==2 ||map[nr][nc]==6 ||map[nr][nc]==7) continue;
			
			visited[nr][nc]=true;
			exit(time+1, nr, nc, visited);
			visited[nr][nc]=false;
		}
		return;
		
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
