import java.util.*;
import java.io.*;

public class Main {
	
	static int T, N, X, Y, beer;
	static class Node implements Comparable<Node>{
		int r;
		int c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Node o) {
			if(this.r != o.r) return this.r-o.r;
			return this.c-o.c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<Node> list;
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			list = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N+2; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new Node(r,c));
			}
			boolean isPossible = bfs(list);
			System.out.println(isPossible?"happy":"sad");
		}
		
	}

	private static boolean bfs(List<Node> list) {
		Queue<Node> qq = new LinkedList<>();
		boolean[] visited = new boolean[N+2];
		qq.offer(new Node(list.get(0).r, list.get(0).c));
		visited[0] = true;
		
		while(!qq.isEmpty()) {
			Node curr = qq.poll();
			int r = curr.r;
			int c = curr.c;
			
			if(r==list.get(list.size()-1).r && c==list.get(list.size()-1).c) {
				return true;
			}
			
			for(int i = 0; i < list.size(); i++) {
				if(visited[i]) continue;
				int distance = Math.abs(curr.r-list.get(i).r)+Math.abs(curr.c-list.get(i).c);
				if(distance > 1000) continue;
				visited[i] = true;
				qq.offer(new Node(list.get(i).r, list.get(i).c));
			}
		}
		
		return false;
	}
}



