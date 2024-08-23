import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _1로만들기2_12852 {
	static int N, cnt;
	static StringBuilder sb; // 출력시간을 줄이기 위해 사용, 근데 성훈님이 굳이 안써도 괜찮다고 하심
//	static int[] nums;
	static List<Integer> nums; // 배열에서 시간초과발생, 리스트로 변경
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
//		cnt=0;
		
		numCycle(N, 0); // 메서드 호출
		System.out.println(min); // 연산의 최소값 출력
//		System.out.println(nums);
		System.out.println(sb); // 1로 만드는 과정의 수 출력
	}

	static void numCycle(int i, int result) { //result : 연산 횟수
		if(i==N) { // 처음 시작한 경우에 리스트 초기화
			nums = new ArrayList<>();
		}
		nums.add(i); // 일단 값을 리스트에 넣는다
		if(result > min) return; // 가지치기 (prunning)
		if(i==1) {
			if(result < min) { // 현재 최솟값보다 작은 연산횟수인 경우를 발견했다면
				sb = new StringBuilder(); // sb초기화
				min = result; // min값 변경
				for(int k = 0; k < nums.size(); k++) {
					if(nums.get(k)==1) break; //배열일 때 미처 빠져나가지 못한 값을 처리하기 위해 작성한 코드. 리스트일때는 의미없음
					sb.append(nums.get(k) + " "); // sb에 추가하기
				}
				sb.append(1); // 1일때 종료하도록 작성했으므로 추가한다.
//				System.out.println("가장작은값 : " + result);
//				System.out.println("min값 : " + min);
			}
//			System.out.println(result + " "+(nums));
			return;
		}else if(i>1) {
			cnt++;
//			System.out.println("i의 값: "+i);
			if(i%3==0) { // 조건1
				numCycle(i/3, result+1);
				nums.remove(nums.size()-1);
			}
			if(i%2==0) { //조건2
				numCycle(i/2, result+1);
				nums.remove(nums.size()-1);
			}
			if(true) { //조건3
				numCycle(i-1, result+1);
				nums.remove(nums.size()-1);
			}	
		}
		return;
	}
}
