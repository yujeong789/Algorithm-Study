import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			int goal = (1 << 10) -1;
			int target = 0;
			int k = 1;
			while(target != goal) {
				String nn = String.valueOf(N*k);
				for(int i = 0; i < nn.length(); i++) {
					target = target | (1 << (nn.charAt(i)-'0'));
				}
				k++;
			}
			System.out.println("#"+t+" "+(N*(k-1)));
		}
	}
}
