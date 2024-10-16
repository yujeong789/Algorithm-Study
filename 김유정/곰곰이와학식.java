import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 곰곰이와학식 {

	static long[] bear;
	static long[] ticket;
	static long sum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		bear = new long[3];
		ticket = new long[3];
		for(int i = 0; i < 3; i++) {
			bear[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 3; i++) {
			ticket[i] = Long.parseLong(st.nextToken());
		}
		
		sum = 0;
		while(true) {			
			boolean isContinue = false;
			
			for(int i = 0; i < 3; i++) {
				sum += Math.min(bear[i], ticket[i]);
				
				if(bear[i] > ticket[i]) { // 곰이 더 많을 때
					bear[i] -= ticket[i];
					ticket[i] = 0;
				}else { // 티켓이 더 많을 때
					ticket[i] -= bear[i];
					bear[i] = 0;
					
					// 남은 티켓은 넘겨준다.
					ticket[(i+1)%3] += ticket[i]/3;
					ticket[i] %= 3;
					
				}
			}
			
			for(int i = 0; i < 3; i++) {
				if(ticket[i] >= 3) isContinue=true;
			}
			if(!isContinue) break;
		}
		
		sum += Math.min(bear[0], ticket[0]);
		System.out.println(sum);
	}

}
