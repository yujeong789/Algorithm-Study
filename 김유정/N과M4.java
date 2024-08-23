import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N과M4 {
	static int N,M;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[M];
		
		numList(0,0);
		System.out.println(sb);
	}
	static void numList(int cnt, int start) {
		if(cnt==M) {
			for(int i = 0; i < nums.length; i++) { //시간 단축을 위함
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = start; i < N; i++) {
			nums[cnt] = i+1;				
			numList(cnt+1, i);
			
			nums[cnt] = 0;
			
		}
	
		
	}
}
