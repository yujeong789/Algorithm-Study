import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int result = escape(0,0);
        System.out.println(result==0 ? "Fail" : result);
    }

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static int escape(int sr, int sc) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr,sc,0,0});
		visited = new boolean[2][N][M];
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int r = curr[0];
			int c = curr[1];
			int t = curr[2];
			int sword = curr[3];
			
			if(t > T) continue;
			if(r==N-1 && c==M-1) return t;
			
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				
				// 그람 획득
				if(map[nr][nc]==2 && !visited[1][nr][nc]) {
					visited[1][nr][nc] = true;
					que.offer(new int[] {nr,nc,t+1, 1});
				}
				
				// 그람 미획득
				else if(!visited[sword][nr][nc]) {
					if(map[nr][nc]==0  || (map[nr][nc]==1 && sword==1)) {
						visited[sword][nr][nc] = true;
						que.offer(new int[] {nr,nc,t+1,sword});
					}
				}
			}
		}
		
		return 0;
	}


	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}