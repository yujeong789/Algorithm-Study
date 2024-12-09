import java.util.*;
import java.io.*;

public class 미생물격리 {
	static class Node{
		int r;
		int c;
		int k;
		int dist;
		Node(int r, int c, int k, int dist){
			this.r = r;
			this.c = c;
			this.k = k;
			this.dist = dist;
		}
	}
	static int T, N, M, K, result;
	static int[][] map;
	static int[] dr = {0,-1,1,0,0}; //상하좌우
	static int[] dc = {0,0,0,-1,1}; //상하좌우
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t<= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			//상: 1, 하: 2, 좌: 3, 우: 4
			Queue<Node> que = new LinkedList<>();
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				que.add(new Node(r,c,k,dist));
			}
			result = 0;
			bfs(M, que);
			System.out.println("#"+t+" "+result);
		}
	}
	private static void bfs(int m, Queue<Node> que) {
		
		while(m>0) {
			Map<String, List<Node>> map = new HashMap<>();		
			int queSize = que.size();
			for(int s = 0; s < queSize; s++) {
				Node now = que.poll();
				now.r += dr[now.dist];
				now.c += dc[now.dist];
				if(now.r==0 || now.r==N-1 || now.c==0 || now.c==N-1) {
					now.k /= 2;
					if(now.dist==1) now.dist=2;
					else if(now.dist==2) now.dist=1;
					else if(now.dist==3) now.dist=4;
					else if(now.dist==4) now.dist=3;
				}
				
				if(now.k==0) continue;
				
				String key = now.r+","+now.c;
				map.putIfAbsent(key, new ArrayList<>());
				map.get(key).add(now);
				
			}

			for(String key : map.keySet()) {
				   List<Node> values = map.get(key);
				   if(values.size()==1) {
					   que.add(values.get(0));
				   }else {
					   Node merge = merged(values);
					   que.add(merge);
				   }
			}
			
			m--;
		}
		
		int sum = 0;
		for(Node n : que) {
			sum+=n.k;
		}
//		System.out.println(sum);
		result = sum;
		
		
	}
	private static Node merged(List<Node> values) {
		int r = 0;
		int c = 0;
		int sum = 0;
		int max = 0;
		int d = 0;
		for(Node now : values) {
			r = now.r;
			c = now.c;
			sum += now.k;
			if(max < now.k) {
				max = now.k;
				d = now.dist;
			}
		}
		return new Node(r,c,sum,d);
	}
}
