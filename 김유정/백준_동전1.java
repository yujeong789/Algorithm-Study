import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_동전1 {

	static int N,K;
	static int[] coin;
	static int[] price;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N];		
		for(int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		price = new int[K+1];
		
		// 가장 작은 단위 동전부터 시작
		price[0]=1;
		for(int c : coin) {
			for(int i = c; i < price.length; i++) {
				price[i] += price[i-c];
			}
		}
//		System.out.println(Arrays.toString(price));
		System.out.println(price[K]);
	}
}
