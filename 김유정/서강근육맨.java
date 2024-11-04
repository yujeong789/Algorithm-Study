import java.util.*;
import java.io.*;

public class 서강근육맨 {
	static int N;
	static long[] num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		num = new long[N];
		for(int i = 0; i < N; i++) {
			num[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(num);
//		System.out.println(Arrays.toString(num));
		long max = 0;
		if(N%2==0) {
			for(int i = 0; i < N/2-1; i++) {
				max = Math.max(max, num[i]+num[N-i-1]);
			}
		}else {
			--N;
			max = Math.max(max, num[N]);
			for(int i = 0; i < N/2; i++) {
//				System.out.println(i +" " + (N-i-1));
				max = Math.max(max, num[i]+num[N-i-1]);
			}
		}
		System.out.println(max);
	}
}
