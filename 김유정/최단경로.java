import java.util.*;
import java.io.*;

public class 최단경로 {
	public static class Node{
		int dest;
		int cost;
		
		public Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
	
	static int V, E, K;
	static int[] cost;
//	static boolean[] visited;
	static ArrayList<Node>[] adjList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		cost = new int[V+1];
//		visited = new boolean[V+1];
		adjList = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[u].add(new Node(v,w));
		}
		
		dijstra(K);
		for(int i = 1; i <= V; i++) {
			if(cost[i]==Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(cost[i]);
		}
	}

	private static void dijstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)->Integer.compare(o1.cost, o2.cost));
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		cost[start] = 0;
		queue.add(new Node(start,0));
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			if(cost[now.dest] < now.cost) continue;
			
			for(Node next : adjList[now.dest]) {
				if(cost[next.dest] > next.cost + now.cost) {
					cost[next.dest] = next.cost + now.cost;
					queue.add(new Node(next.dest, cost[next.dest]));
				}
			}
		}
		
	}
}
