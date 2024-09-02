import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 숫자야구 {
	static int N;
	static int[] num, strike, ball;
	static List<Integer> list = new ArrayList<>(); // 배열 크기 정하기 어려워서 list 사용
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		strike = new int[N];
		ball = new int[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			num[i] = Integer.parseInt(st.nextToken());
			strike[i] = Integer.parseInt(st.nextToken());
			ball[i] = Integer.parseInt(st.nextToken());
		}
		// 입력-------------------
		// 연산-------------------
		// 브루트포스 -> 모든 경우의 수 전부 돌아봄 
		// 중복된 수는 없으므로 123~987까지 돈다
		for(int i = 123; i <= 987; i++) {
			boolean check = true; // 조건이 일치하는지 확인하기 위한 변수
			int a = i/100; // 100의자리
			int b = (i/10)%10; //10의자리
			int c = i%10; // 1의 자리
			if(a==b || a==c || b==c) continue; // 각 자리마다 숫자는 달라야 한다
			if(b==0 || c==0) continue; // 1~9까지의 숫자
			
			// 입력받은 숫자 탐색 (민혁이 질문)
			for(int j=0; j<N; j++) {
				int t1 = num[j]/100; // 100의자리
				int t2 = (num[j]/10)%10; //10의자리
				int t3 = num[j]%10; //1의자리
				
				int S=0, B=0; // 민혁이 질문 안에서만 사용할 변수 (strike, ball)
				
				if(a==t1) S++; // 숫자, 자리 동일하면 strike++
				if(b==t2) S++; // 숫자, 자리 동일하면 strike++
				if(c==t3) S++; // 숫자, 자리 동일하면 strike++
				
				if (a==t2 || a==t3) B++; // 숫자만 동일하면 ball++
				if (b==t1 || b==t3) B++; // 숫자만 동일하면 ball++
				if (c==t1 || c==t2) B++; // 숫자만 동일하면 ball++
				
				if(S!=strike[j] || B!=ball[j]) {
					check=false; // 주어진 조건과 동일하면 동일하다고 체크
					break;
				}
			}
			if(check) list.add(i); // 모든 조건과 일치하는 숫자라면 list에 넣는다.
		}
		System.out.println(list.size()); // 리스트 안의 원소 개수 출력	
	}
}
