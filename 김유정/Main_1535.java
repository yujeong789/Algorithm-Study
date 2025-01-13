import java.util.*;
import java.io.*;

public class Main {
	static int N, maxHealth, maxHappy;
	static int[] healths, happys;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		healths = new int[N];
		happys = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			healths[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			happys[i] = Integer.parseInt(st.nextToken());
		}
		
		maxHealth = 0;
		maxHappy = 0;
		dfs(0, 100, 0);
		System.out.println(maxHappy);
	}
	
	static void dfs(int cnt, int health, int happy) {
//		System.out.println("cnt "+cnt+" health "+health+" happy "+happy);
		if(cnt==N && health>0) {
			maxHappy = Math.max(maxHappy, happy);
			return;
		}
		if(health<=0) return;

		dfs(cnt+1, health-healths[cnt], happy+happys[cnt]);
		dfs(cnt+1, health, happy);
	}
}
