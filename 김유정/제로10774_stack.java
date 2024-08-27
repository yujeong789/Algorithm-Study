import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class 제로10773 {
	static int[] nums; // 내가 편하게 볼라고 만든 배열
	static int sum, i;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		int K = Integer.parseInt(br.readLine());
		
		for(int k = 0; k < K; k++) {
			int n = Integer.parseInt(br.readLine());
			if(n==0) {
				stack.pop();
				continue;
			}
			stack.push(n);
		}
		int size = stack.size();
		for(int i = 0; i < size; i++) {
			sum += stack.pop();
		}
		System.out.println(sum);

	}
}
