import java.util.*;
import java.io.*;

public class 알고스팟 {
	
	static class Node{
		int r;
		int c;
		int value;
		
		Node(int r, int c, int value){
			this.r = r;
			this.c = c;
			this.value = value;
		}
	}
	
	static int N, M;
	static int[][] map, cost;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		cost = new int[M][N];
		for(int i = 0; i < M; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				map[i][j] = tmp[j]-'0';
				cost[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dijkstra();
		System.out.println(cost[M-1][N-1]);

	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->(o1.value-o2.value));
		pq.add(new Node(0,0,0));
		cost[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int r = now.r;
			int c = now.c;
			int value = now.value;
			
			if(value > cost[r][c]) continue;
			
			for(int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(cost[nr][nc] <= value+map[nr][nc]) continue;
				cost[nr][nc] = value+map[nr][nc];
				pq.add(new Node(nr,nc,cost[nr][nc]));
			}
		}
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<M && c>=0 && c<N;
	}
}
