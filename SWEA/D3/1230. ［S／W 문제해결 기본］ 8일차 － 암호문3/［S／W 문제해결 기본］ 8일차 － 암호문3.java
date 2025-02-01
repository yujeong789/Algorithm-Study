import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		LinkedList<String> list;
		T = 10;
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			list = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				list.offer(st.nextToken());
			}
			
			int cnt = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < cnt; i++) {
				char order = st.nextToken().charAt(0);
				
				
				if(order=='I'){
					int X = Integer.parseInt(st.nextToken());
					int Y = Integer.parseInt(st.nextToken());
					for(int y = 0; y < Y; y++) {
						String s = st.nextToken();
						list.add(X++, s);
					}
				}else if(order=='D'){
					int X = Integer.parseInt(st.nextToken());
					int Y = Integer.parseInt(st.nextToken());
					for(int y = 0; y < Y; y++) {
						list.remove(X+1);
					}
					continue;
				}else if(order=='A'){
					int Y = Integer.parseInt(st.nextToken());
					for(int y = 0; y < Y; y++) {
						String s = st.nextToken();
						list.add(s);
					}
				}
					
			}
			System.out.print("#"+t+" ");
			for(int i = 0; i < 10; i++) {
				System.out.print(list.get(i)+" ");
			}System.out.println();
		}
	}
}
