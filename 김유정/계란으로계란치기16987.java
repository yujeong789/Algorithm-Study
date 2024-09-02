package Day_0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 계란으로계란치기16987 {
	static int N, max; //계란의수
	static int[] eggStrength, eggWeight;
	static int[] nums;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
//		int count=0;
		max = 0;
		eggStrength = new int[N]; // 내구도
		eggWeight = new int[N]; // 무게
		nums = new int[2];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggStrength[i] = Integer.parseInt(st.nextToken());
			eggWeight[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(Arrays.toString(eggStrength));
		System.out.println(Arrays.toString(eggWeight));
		eggCount(0,0);
		System.out.println(max);
	}
	
	static void eggCount(int depth, int count) {
		// 가장 오른쪾 달걀까지 가면 종료
		if(depth==N) {
			max = Math.max(max, count);
			return;
		}
		
		// 깨진달걀로 때릴 수 없다.
		// 나 빼고 다 깨져있으면 할게 없다.
		if(eggStrength[depth]<=0 || count==N-1) {
			eggCount(depth+1, count);
			return;
		}
		
		int cnt = count;
		for(int i = 0; i < N; i++) {
			// 자기자신은 깰 수 없다.
			if(i==depth) continue;
			// 이미 깨진달걀은 깰 수 없다.
			if(eggStrength[i]<=0) continue;
			
			// 계란 깨기
			eggStrength[i] -= eggWeight[depth];
			eggStrength[depth] -= eggWeight[i];
			if(eggStrength[i]<=0) count++;
			if(eggStrength[depth]<=0) count++;
//			System.out.println(count + Arrays.toString(eggStrength));
			eggCount(depth+1, count);
			eggStrength[i] += eggWeight[depth];
			eggStrength[depth] += eggWeight[i];
			count = cnt;
		}
		
		
		
		
		
	}
}

/*/
3
8 5
1 100
3 5

3

3
1 100
8 5
3 5

2
*/
