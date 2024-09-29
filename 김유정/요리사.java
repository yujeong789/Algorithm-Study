import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 요리사 {
	static int T;
	static int N;
	static int[][] map;
	static int food1, food2;
	static int min;
	static int[] num;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			num = new int[N];
			visited = new boolean[N];
			for(int i = 0; i < N; i++) {
				num[i] = i;
			}
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
				
			}
			min = Integer.MAX_VALUE;
			dfs(0,0); // 두개 그룹으로 나누기
			System.out.println("#"+t+" "+min);
		}
	}

	// 두개 그룹으로 나누기 -> 조합
	private static void dfs(int cnt, int n) {
		if(cnt==N/2) { // 두개 그룹으로 나누기
//			System.out.println(Arrays.toString(visited));
			int[] A = new int[N/2];
			int[] B = new int[N/2];
			int a=0;
			int b=0;
			for(int i = 0; i < N; i++) {
				if(visited[i]) A[a++]=i;
				else B[b++]=i;
			}
//			System.out.println(Arrays.toString(A));
//			System.out.println(Arrays.toString(B));
			makeFood(A,B);
//			System.out.println();
			return;
		}
		for(int i = n; i < N; i++) {
			if(visited[i]) continue;
			visited[i]=true;
			dfs(cnt+1, i+1);
			visited[i]=false;
		}
	}

	// 두개 그룹의 시너지 Sij값의 차이 구하기
	private static void makeFood(int[] A, int[] B) {
		int resultA = taste(A);
		int resultB = taste(B);
//		System.out.println("A: " + resultA + " B: " + resultB);
		min = Math.min(min, Math.abs(resultA-resultB));
		return;
	}

	// 두개 그룹의 시너지 Sij값 구하기
	private static int taste(int[] L) {
		int sum=0;
		for(int i : L) {
			for(int j : L) {
				if(i==j) continue;
				sum += map[i][j];
			}
		}
		return sum;
	}
}
