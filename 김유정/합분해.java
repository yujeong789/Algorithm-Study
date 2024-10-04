import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;

// 중복순열의 합 -> 시간초과
// dp

public class 합분해 {
	
	static int N,K;
	static int s;
	static int max;
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //0~N
		K = Integer.parseInt(st.nextToken()); //K개 더하기
		int[][] dp = new int[N+1][K+1];
		
		for(int n = 0; n <= N; n++) {
			dp[n][0]=0;
		}
		for(int k = 0; k <= K; k++) {
			dp[0][k]=1;
		}
		for(int n = 1; n <= N; n++) {
			for(int k=1; k <= K; k++) {
				dp[n][k] = (dp[n-1][k]+dp[n][k-1])%1000000000; // 오버플로우 발생 -> 중간중간 나눠줘야함
			}
		}
		System.out.println(dp[N][K]%1000000000);
	}
}
