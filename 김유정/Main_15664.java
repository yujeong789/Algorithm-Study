import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] nums, result;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		result = new int[M];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		comb(0, 0);
	}

	private static void comb(int cnt, int start) {
		if(cnt==M) {
			for(int i = 0; i < M; i++) {
				System.out.print(result[i]+" ");
			}System.out.println();
			return;
		}
		
		int before = 0;
		for(int i = start; i < N; i++) {
			if(visited[i]) continue;
			if(before==nums[i]) continue;
			visited[i] = true;
			result[cnt] = nums[i];
			before = nums[i];
			comb(cnt+1, i);
			visited[i] = false;
		}
	}
}
