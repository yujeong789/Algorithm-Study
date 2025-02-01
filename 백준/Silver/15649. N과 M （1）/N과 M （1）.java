import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 1~N까지 중, M개 선택
	static int[] nums;
	static int[]  p;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[M];
		p = new int[N];
		Arrays.setAll(p, i -> i+1);
		visited = new boolean[N];
		nCr(0, M);
		
	}

	static void nCr(int cnt, int M) {
		if(cnt == M) {
			for(int i = 0; i < nums.length; i++) {
				System.out.print(nums[i] + " ");
			}
			return;
		}
		for(int i = 0; i < p.length; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			nums[cnt] = p[i];
			nCr(cnt+1, M);
			nums[cnt] = 0;
			visited[i] = false;
		}
		
	}
}
