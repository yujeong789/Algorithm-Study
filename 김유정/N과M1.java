import java.util.Arrays;
import java.util.Scanner;

public class N과M1 {
	static int N, M;
	static int[] nums;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		nums = new int[M];
		visited = new boolean[N];
		
		numList(0);
	}

	static void numList(int cnt) {
		if(cnt == M) {
			System.out.println(Arrays.toString(nums));
			return;
		}
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			nums[cnt] = (i+1);
			numList(cnt+1);
			nums[cnt] = (i+1);
			visited[i] = false;
		}
		
	}
}
