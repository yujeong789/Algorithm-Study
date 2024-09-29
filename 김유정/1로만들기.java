import java.util.Scanner;

public class 백준_1로만들기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] sum = new int[N+1];
		
		for(int i = 2; i < N+1; i++) {
			int tmp1=Integer.MAX_VALUE;
			int tmp2=Integer.MAX_VALUE;
			int tmp3=Integer.MAX_VALUE;
			if(i%3==0) {
				tmp1=sum[i/3]+1;
			}
			if(i%2==0) {
				tmp2=sum[i/2]+1;
			}
			if(true) {
				tmp3=sum[i-1]+1;
			}
			sum[i] = Math.min(tmp1, Math.min(tmp2, tmp3));
			
		}
		System.out.println(sum[N]);
	}
}
