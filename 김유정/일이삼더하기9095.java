import java.util.Scanner;

public class 일이삼더하기9095 {
	static int T, n, result, cnt, sum;
	static int[] p = {1,2,3};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			n = sc.nextInt();
			result=0;
			cnt=0;
			sum=0;
			number(0, 0);
			System.out.println(result);
		}
		
	}

	static void number(int cnt, int sum) {
		if(sum==n) {
			result++;
			return;
		} else if(cnt>n) {
			return;
		}
		for(int i = 0; i < p.length; i++) {
			number(cnt+1, sum+p[i]);
		}
		
	}
}
