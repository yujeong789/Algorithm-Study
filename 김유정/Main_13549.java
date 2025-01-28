import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		int ans = bfs(N, K);
		System.out.println(ans);
	}

	private static int bfs(int N, int K) {
		Deque<int[]> que = new LinkedList<>();
		que.offer(new int[] {N, 0});
		boolean[] visited = new boolean[100_001];
		visited[N] = true;
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int n = curr[0];
			int t = curr[1];
			
			if(n==K) {
				return t;
			}
			
			int n3 = n*2;
			if(n3 <= 100000 && !visited[n3]) {
				visited[n3] = true;
				que.addFirst(new int[] {n3, t});
			}

			int n1 = n-1;
			if(n1 >= 0 && !visited[n1]) {
				visited[n1] = true;
				que.addLast(new int[] {n1, t+1});
			}
			
			int n2 = n+1;
			if(n2 <= 100000 && !visited[n2]) {
				visited[n2] = true;
				que.addLast(new int[] {n2, t+1});
			}
			
			
			
		}
		
		return -1;
	}
}