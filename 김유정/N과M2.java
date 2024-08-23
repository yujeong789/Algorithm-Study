import java.util.Arrays;
import java.util.Scanner;

public class N과M2 {
	static int N, M;
	static int[] nums;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	
		M = sc.nextInt();
		nums = new int[M];
		visited = new boolean[N];
		comTest(0, 0);
	}

	static void comTest(int cnt, int start) {
		if(cnt == M) {
			System.out.println(Arrays.toString(nums));
			return;
		}
		for(int i = start; i < N; i++) {
			nums[cnt] = (i+1);
			comTest(cnt+1, i+1);
			nums[cnt] = 0;
		}
		
	}
}
