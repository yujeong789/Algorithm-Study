import java.io.*;

public class Main {
    static int N;
    static char[] medicine;
    static int[][] dp;
    static char[] order = {'B', 'L', 'D'};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        medicine = br.readLine().toCharArray();
        dp = new int[3*N][3*N];
        
        for(int i = 0; i < 3*N; i++) {
            for(int j = 0; j < 3*N; j++) {
                dp[i][j] = -1;
            }
        }
        
        System.out.println(solve(0, 3*N-1, 0));
    }
    
    static int solve(int left, int right, int count) {

        if(left > right) return 0;
        if(dp[left][right] != -1) return dp[left][right];
        
        int result = 0;
        char needed = order[count % 3]; 
        
        if(medicine[left] == needed) {
            result = Math.max(result, solve(left+1, right, count+1) + 1);
        }
        
        if(medicine[right] == needed) {
            result = Math.max(result, solve(left, right-1, count+1) + 1);
        }
        
        dp[left][right] = result;
        return result;
    }
}