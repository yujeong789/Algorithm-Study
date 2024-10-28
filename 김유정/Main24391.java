import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main24391 {
	static int N, M;
	static int[] parent, rank, lector;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		rank = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		
		// 건물 연결 
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			union(i,j);
		}
		
		// 강의실 이동 확인
		lector = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			lector[i] = find(Integer.parseInt(st.nextToken()));
		}
//		System.out.println(Arrays.toString(lector));

		// 강의실 이동 횟수
		int result = 0;
		for(int i = 1; i <= N; i++) {
			if(lector[i] == lector[i-1]) continue;
			result++;
			
		}
		System.out.println(result-1);
	}

	private static void union(int i, int j) {
		i = find(i);
		j = find(j);
		
//		if(i==j) return;
		if(rank[i] < rank[j]) {
			parent[i] = j;
			rank[j] += rank[i];
		}else {
			parent[j] = i;
			rank[i] += rank[j];
		}
		return;
	}

	private static int find(int i) {
		if(parent[i]==i) return i;
		return parent[i] = find(parent[i]);
	}
}
