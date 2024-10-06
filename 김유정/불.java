import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 동서남북 인접한 칸으로 이동할 수 있다
// 벽을 통과할 수 없다 #
// 불이 옮겨진 칸이나 옮겨지려는 칸으로 이동할 수 없다 -> 불을 먼저 bfs돌리고 상근이는 dfs로 위치 이동
// 현재 있는 칸에 불이 붙는것과 동시에 다른 칸으로 이동할 수 있다.
// 불 bfs -> 불이 이동할 칸 표시 -> 상근이 이동 -> 불이동
// r이나 c가 0이면 탈출, w*h보다 많이 이동했으면 그냥 impossible

// dfs -> 시간 초과 -> bfs만의 반복
public class 불 {
	
	static int T,W,H;
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> fire;
	static Queue<int[]> person;
	static int time;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W= Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			visited = new boolean[H][W];
			for(int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
			}
			//입력----
			//연산----
			fire = new LinkedList<>(); //불이들어갈 리스트
			person = new LinkedList<>(); //상근이가 들어갈 리스트
			
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) 
					if(map[i][j]=='@') {
						person.offer(new int[] {i,j}); //상근이의위치
						visited[i][j]=true;
					}
					else if(map[i][j]=='*') {
						fire.offer(new int[] {i,j}); // 불의위치
					}
				}
	
			
			int result = bfs();
			
			if(result==-1) System.out.println("IMPOSSIBLE");
			else System.out.println(time);
		}
	}
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	private static int bfs() {
		time = 1; // 첫번째 시도이므로 시간이 1초일때라 가정
		while(!person.isEmpty()) { // 상근이가 움질일 곳이 없을 때까지
			int firesize = fire.size();
			for(int f = 0; f < firesize; f++) {
				int[] curr = fire.poll();
				int r = curr[0];
				int c = curr[1];
				
				for(int d = 0; d < 4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(!check(nr,nc)) continue;
					if(map[nr][nc]=='#' || map[nr][nc]=='*') continue;
					map[nr][nc]='*'; //불로만들기
					fire.offer(new int[] {nr,nc});
				}
			}
			int personsize = person.size();
			for(int p = 0; p < personsize; p++) {
				int[] curr = person.poll();
				int r = curr[0];
				int c = curr[1];
				
				for(int d = 0; d < 4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(!check(nr,nc)) return time+1;
					if(map[nr][nc]=='*' || map[nr][nc]=='#') continue;
					if(visited[nr][nc]) continue;
					if(map[nr][nc]=='.') {
						visited[nr][nc]=true;
//						map[nr][nc]='@'; //불로만들기
						person.offer(new int[] {nr,nc});
					}
				}
			}
			time++;
		}
		return -1;
	}
	
	private static boolean check(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W;
	}
}
