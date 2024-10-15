import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준123더하기4 {
	static int T,N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[3][N+1];
			map[0][0] = 1;
			map[0][1] = 1;
			for(int j = 2; j < N+1; j++) {
				map[0][j] = map[0][j-1];
				if(j-2>=0) map[1][j] = map[0][j-2]+map[1][j-2];
				if(j-3>=0) map[2][j] = map[0][j-3]+map[1][j-3]+map[2][j-3];
			}
			System.out.println(map[0][N]+map[1][N]+map[2][N]);
		}
	}
}
