import java.util.Scanner;

public class 백준_설탕배달 {

	static int N, aws;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int tmp=0;
		while(true) {
			if(N%5==0) {
				aws = N/5;
				break;
			}else {
				N-=3;
				tmp++;
			}
			if(N<0) {
				aws=-1;
				tmp=0;
				break;
			}
		}
		System.out.println(aws+tmp);
	}
}
