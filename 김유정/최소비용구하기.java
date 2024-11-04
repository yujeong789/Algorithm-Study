import java.io.*;
import java.util.*;

public class 최소비용구하기 {

	public static class Node{
		int dest;
		int cost;
		
		public Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
	
	static int N, M;
	static ArrayList<Node>[] adjList;
	static int[] cost;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		cost = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adjList[a].add(new Node(b, c)); //단방향연결
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijstra(start, end);
		System.out.println(cost[end]);
				
	}

	private static void dijstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		cost[start] = 0;
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(cost[now.dest] < now.cost) continue;
			
			for(Node next : adjList[now.dest]) {
				if(cost[next.dest] > next.cost + now.cost) {
					cost[next.dest] = next.cost + now.cost;
					pq.add(new Node(next.dest, cost[next.dest]));
				}
			}

		}
	}
}
