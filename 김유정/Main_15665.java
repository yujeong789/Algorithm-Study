import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] nums, result;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		result = new int[M];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		sb = new StringBuilder();
		comb(0, 0);
		System.out.println(sb);
	}

	private static void comb(int cnt, int start) {
		if(cnt==M) {
			for(int i = 0; i < M; i++) {
				sb.append(result[i]).append(" ");
			} sb.append("\n");
			return;
		}
		
		int before = 0;
		for(int i = 0; i < N; i++) {
			if(before==nums[i]) continue;
			result[cnt] = nums[i];
			before = nums[i];
			comb(cnt+1, 0);
		}
	}
}
