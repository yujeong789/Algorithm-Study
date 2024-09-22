package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 문자열의 뒤에 A를 추가한다. -> 마지막 문자열이 A면 그냥 뺀다
// 문자열을 뒤집고 뒤에 B를 추가한다. -> 마지막 문자가 B면 빼고 뒤집는다.
public class a와b2 {
	static StringBuilder S,T;
	static int num;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = new StringBuilder(br.readLine());
		T = new StringBuilder(br.readLine());
		
		while(S.length() < T.length()) {
			if(T.charAt(T.length()-1) == 'A') { // 마지막 문자열이 A이면 그냥 뺀다
				T.deleteCharAt(T.length()-1);
			}else { //마지막 문자가 B이면 빼고 뒤집는다.
				T.deleteCharAt(T.length()-1);
				T.reverse();
			}
		}
		System.out.println(T.toString().equals(S.toString())?1:0);
	}
	
}
