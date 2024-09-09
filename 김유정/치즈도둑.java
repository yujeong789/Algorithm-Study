package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈도둑 {
	static int T;
	static int N;
	static int[][] map;
	static int[][] visited;
	static int[][] copy;
	static int max,count;
	static int lastDay;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			lastDay = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					lastDay = Math.max(lastDay, map[i][j]);
				}
			}
			//입력--------
			//연산--------
			max = 0;
			count = 0;
			int day=0;
			while(day <= 100) { //lastDay
				int group = 1;
				visited = new int[N][N];
				copy = map.clone();

				eat(day); //각 날짜에 맞는치즈를 먹어치운다
				
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] != 0 && visited[i][j]==0) { //치즈가 있고 방문하지 않은 곳
							cheeseCnt(i,j, group++); //bfs
						}
					}
				}
				count = group-1;
				max = Math.max(max, group-1);
				day++;
				map = copy.clone();

			}
			System.out.println("#"+t+" "+max);
		}//tc
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	//치즈가 몇개잇는지 확인
	private static void cheeseCnt(int sr, int sc, int group) {
//		System.out.println("group : " + group);
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr,sc});
//		int num = 1;
		visited[sr][sc] = group;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0];
			int c = cur[1];
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue; //범위안에없으면 continue;
				if(map[nr][nc]==0) continue;// 이미먹은치즈면 continue;
				if(visited[nr][nc]==0) {
					visited[nr][nc] = group; //범위안에 있고 아직 안먹은 치즈이면
					que.offer(new int[] {nr,nc});					
				}
			}
			
		}
		
	}

	//범위안에잇는지 체크하는 로직
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

	//날짜에 맞는 숫자의 치즈를 먹엇다
	private static void eat(int day) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j]<=day) map[i][j] = 0;
			}
		}
	}

	
}

/*
2
2
1 2
2 1
5
6 8 2 6 2
3 2 3 4 6
6 7 3 3 2
7 2 5 3 6
8 9 5 2 7
 */