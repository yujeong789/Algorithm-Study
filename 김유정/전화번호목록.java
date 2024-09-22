package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 왜 배열 대신 해시맵으로 푸는 것인가 -> 맵의 key에 이러한 값이 있는지 한방에 찾을 수 있어서

public class 전화번호목록 {
	static int T;
	static int N;
	static String[] phone;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			phone = new String[N];
			for(int i = 0; i < N; i++) {
				phone[i] = br.readLine();
			}
			
			//처음에는 길이순으로 정렬해서 이중포문 사용하려함 -> 시간초과
//			Arrays.sort(phone, new Comparator<String>() {
//				
//				public int compare(String o1, String o2) {
//					return o1.length()-o2.length();
//				}
//			});

			Arrays.sort(phone);
			
			boolean check = false;
			일관성탐색 : 
			for(int i = 0; i < N-1; i++) {
				if(phone[i+1].startsWith(phone[i])) {
					check = true;
					break 일관성탐색;
				}
			}
			
			String answer = check?"NO":"YES";
			sb.append(answer).append("\n");
		}
		//출력
		System.out.println(sb);
	}
	
}
