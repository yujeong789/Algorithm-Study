import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N과M5 {
	static int N,M;
	static int[] givennums, nums;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		givennums = new int[N]; // 주어진 숫자들
		nums = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			givennums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(givennums); // 주어진 숫자 정렬
		numList(0);
		
		System.out.println(sb);
		
	}
	static void numList(int cnt) {
		if(cnt==M) {
			for(int i = 0; i < nums.length; i++) { // 시간단축을 위함
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			nums[cnt] = givennums[i];
			numList(cnt+1);
			nums[cnt] = 0;
			visited[i] = false;
		}
	}
}
