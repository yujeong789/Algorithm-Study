import java.util.*;
import java.io.*;

public class 여행가자 {
	static int N, M;
	static int[] parent, rank;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		rank = new int[N+1];
		
		// makeset
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				if(Integer.parseInt(st.nextToken())==1) union(i,j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int city = find(Integer.parseInt(st.nextToken()));
		boolean isConnect = true;
		for(int i = 0; i < M-1; i++) {
			if(city == find(Integer.parseInt(st.nextToken()))) continue;
			else isConnect = false;
		}
		
		if(isConnect) System.out.println("YES");
		else System.out.println("NO");
	}

	private static void union(int i, int j) {
		int pi = find(i);
		int pj = find(j);
		
		if(rank[pi] >= rank[pj]) {
			rank[pi] += rank[pj];
			parent[pj] = pi;
		}else {
			rank[pj] += rank[pi];
			parent[pi] = pj;
		}
		
	}

	private static int find(int i) {
		if(parent[i]==i) return i;
		return i = find(parent[i]);
	}
	
	
}
