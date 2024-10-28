import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17352 {
	static int N;
	static int[] parent, rank;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		rank = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		for(int i = 0; i < N-2; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a,b);
		}
		System.out.println(Arrays.toString(parent));
		for(int i = 1; i <= N; i++) {
			if(parent[i]==i) System.out.print(i+" ");
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
		
	}

	private static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
	}
}
