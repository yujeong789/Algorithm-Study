import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기1759 {
	static int L, C;
	static char[] input;
	static char[] alpha;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new char[C];
		alpha = new char[L];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		// 입력----------------
		// 연산----------------
		
		Arrays.sort(input); //입력받고 오름차순으로 정렬
		combination(0, 0); // 조합 호출
		System.out.println(sb); // 정답출력
	}

	static void combination(int cnt, int start) {
		if(cnt==L) { // 종료조건
			int 모음개수=0;
            int 자음개수=0;
			for(int i = 0; i < L; i++) {
				if("aeiou".contains(Character.toString(alpha[i]))) 모음개수++;
                else 자음개수++;
			}
			//모음개수가 1개이상이고 자음개수가 2개이상일때만 출력
			if(모음개수>=1 && 자음개수>=2) {
				for(int i = 0; i < L; i++) {
					sb.append(alpha[i]);
				}
				sb.append("\n"); // 한줄 띄우기
				return;				
			}
            return;
		}
		// 조합공식
		for(int i = start; i < C; i++) {
			alpha[cnt] = input[i];
			combination(cnt+1, i+1);
			alpha[cnt] = 0;
		}
		
	}
}