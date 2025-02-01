import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			boolean check = false;
			if((M&((1<<N)-1)) == (1<<N)-1) check = true;
			
			System.out.println("#"+t+" "+(check ? "ON" : "OFF"));
		}
	}
}
