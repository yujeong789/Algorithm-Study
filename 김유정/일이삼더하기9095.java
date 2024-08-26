import java.util.Scanner;

public class 일이삼더하기9095 {
	static int T, n, result, cnt, sum;
	static int[] p = {1,2,3};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			n = sc.nextInt(); // 입력
			result=0; // 몇가지 경우의 수인지 확인
			cnt=0; // 재귀함수를 반복하는 횟수
			sum=0; // 재귀함수를 반복하며 나온 수를 더한 값
			number(0, 0); // 재귀함수 호출
			System.out.println(result); // static 변수로 선언하여 바로 출력
		}
		
	}// 입력받기

	// 연산
	static void number(int cnt, int sum) {
		// 종료조건1 : sum이 목표에 다다르면 return
		if(sum==n) { 
			result++;
			return;
		} 
		// 종료조건2 : sum이 목표에 다다르지 못한다면 1만 더했을 때를 생각해서 
		// cnt가 n보다 커지면 종료
		else if(cnt>n) { 
			return;
		}
		// 반복
		for(int i = 0; i < p.length; i++) {
			number(cnt+1, sum+p[i]);
		}
		
	}
}
