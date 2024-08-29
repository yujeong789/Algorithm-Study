import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 로또6603 {
	static List<Integer> list;
	static int[] nums;
	static int N, R;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();
			list = new ArrayList<>();
			if(Integer.parseInt(st.nextToken())==0) {
				break; // 0이 들어오면 종료
			}
			while(st.hasMoreTokens()) { // 입력받기
				list.add(Integer.parseInt(st.nextToken()));
			}
//			System.out.println(nums); // 입력 확인용 출력
			// 입력 -------------
			// 연산 -------------
			
			N = list.size(); // 리스트의 크기만큼
			R = 6; // 선택하려는 수의 개수만큼
			nums = new int[R];
			
			combination(0,0);
			System.out.println(sb);
		}
	}

	static void combination(int cnt, int start) {
		if(cnt==R) {
			for(int i = 0; i < R; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = start; i < N; i++) {
			nums[cnt] = list.get(i);
			combination(cnt+1, i+1);
//			nums[cnt] = 0;
		}
	}
}
