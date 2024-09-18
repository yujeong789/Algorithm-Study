package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class flyMeToTheAlphaCentauri {
	static int T;
	static long x,y;
	static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())+1;
			y = Integer.parseInt(st.nextToken())-1; //마지막은 무조건 1칸 움직여야하므로 1을 빼준다
			
			min=Integer.MAX_VALUE;
			dfs(2,1,x,y); // 움직인 횟수, 움직이는 범위(광년), 시작, 끝
			System.out.println(min); // 마지막 한칸 움직임
		}
	}

	private static void dfs(int cnt, int range,long start, long goal) {
		if(start==goal) { // 도착 시 종료
			min = Math.min(min, cnt);
			return;
		}
		if(cnt>min) return; //가지치기
		
		if(start+range+1<=goal) { // 목표치를 넘지 않을 때
			dfs(cnt+1, range+1, start+range+1, goal);			
		}
		if(range>0 && start+range<=goal) { // 범위가 양수이고 목표치를 넘지 않을 때
			dfs(cnt+1, range, start+range, goal);
		}
		if(range-1>0 && start+range-1<=goal) { // 범위가 양수이고 목표치를 넘지 않을 때
			dfs(cnt+1, range-1,start+range-1,goal);			
		}
		
	}
	
}
