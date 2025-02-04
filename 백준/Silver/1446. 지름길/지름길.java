import java.util.*;
import java.io.*;

public class Main {
	
	static class Node implements Comparable<Node>{
//		int start;
		int end;
		int shortcut;
		int distance;
		Node(int end, int shortcut, int distance){
//			this.start = start;
			this.end = end;
			this.shortcut = shortcut;
			this.distance = distance;
		}
		@Override
		public int compareTo(Main.Node o) {
			return o.distance-this.distance;
		}
		
	}
	static int N, D;
	static LinkedList<Node>[] list;
//	static Queue<Node> que;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		list = new LinkedList[D+1];
		for(int i = 0; i <= D; i++) {
			list[i] = new LinkedList<>();
		}
//		que = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int shortcut = Integer.parseInt(st.nextToken());
			int distance = (end-start)-shortcut;
			
			if(start < 0 || end > D) continue;
			if(distance < 0) continue;
			
			list[start].offer(new Node(end, shortcut, distance));
//			System.out.println(start+" "+end+" "+shortcut);
		}
	
		
		int ans = bfs(list);
		System.out.println(ans);
	}
	
	private static int bfs(LinkedList<Main.Node>[] list) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1]-o2[1]));
		pq.offer(new int[] {0,0});
		boolean[] visited = new boolean[D+1];
		visited[0] = true;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int end = curr[0];
			int distance = curr[1];
			
			if(end==D) return distance;
			if(list[end].size()==0) {
				pq.offer(new int[] {end+1, distance+1});
//				System.out.println("리스트길이가0/ "+end+" "+distance);				
			}
			for(Node n : list[end]) {
				pq.offer(new int[] {n.end, distance+n.shortcut});
				pq.offer(new int[] {end+1, distance+1});
//				System.out.println(end+" "+distance);
			}
		}

		return 0;
	}
}



