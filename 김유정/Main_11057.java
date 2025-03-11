import java.io.*;
import java.nio.channels.IllegalChannelGroupException;
import java.util.*;

public class Main {
	static int N;
	static int[][] dp;
	static final int MOD = 10007;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][10];
        
        for(int j = 0; j <= 9; j++) {
            dp[1][j] = 1;
        }
        
        for(int i = 2; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j] + dp[i-1][k]) % MOD;
                }
            }
        }
        
        int result = 0;
        for(int j = 0; j <= 9; j++) {
            result = (result + dp[N][j]) % MOD;
        }
        
        System.out.println(result);
    }
}
