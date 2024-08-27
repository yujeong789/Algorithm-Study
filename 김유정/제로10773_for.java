import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 제로10773 {
	static int[] nums; // 내가 편하게 볼라고 만든 배열
	static int sum, i;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		i = 0; // 인덱스
		sum=0; // 총합
		nums = new int[K]; // 숫자들을 담는 배열, 없어도 ㄱㅊ
		for(int k = 0; k < K; k++) {
			int n = Integer.parseInt(br.readLine()); // 입력
			// 잘못입력한 경우 (0)
			if(n==0) {
				sum -= nums[--i]; // 합에서 빼고
				nums[i]=0; // 배열에서도 뺀다
				continue; // 아래는 안돌도록 
			}
			nums[i] = n; // 배열에 추가
			sum += n; // 합에 추가
			i++; // 인덱스 올려
		}
		System.out.println(sum); // 합 출력
	}
}
