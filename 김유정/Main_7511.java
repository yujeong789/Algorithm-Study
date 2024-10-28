import java.util.*;
import java.io.*;

public class Main_7511 {

	static int T, N, K, M;
	static int[] parent, rank;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			K = Integer.parseInt(br.readLine());
			
			parent = new int[N+1];
			rank = new int[N+1];
			for(int i =1; i <= N; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())+1;
				int b = Integer.parseInt(st.nextToken())+1;
				
				union(a,b);
			}
//			System.out.println(Arrays.toString(parent));
			System.out.println("Scenario "+t+":");
			M = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())+1;
				int b = Integer.parseInt(st.nextToken())+1;
				
				if(find(a)==find(b)) System.out.println(1);
				else System.out.println(0);
			}
			System.out.println();
			
		}
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		if(rank[a] < rank[b]) {
			parent[a] = b;
			rank[b] += rank[a];
		}else {
			parent[b] = a;
			rank[a] += rank[b];
		}
		return;
	}

	private static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
	}
}
