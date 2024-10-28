package boj;

import java.util.*;
import java.io.*;

public class Main_24542 {
 
	static int N, M;
	static int[] parent, rank, group;
	static int value = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		rank = new int[N+1];
		group = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
		int result = 1;
		for(int i = 1; i <= N; i++) {
			if(find(i)==i) {
				result = (int)((long)result * rank[i]%value);				
			}
		}
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
		System.out.println(result%value);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		
		if(rank[a] < rank[b]) {
			parent[a] = b;
			rank[b] += rank[a];
			rank[a]=0;
		}else {
			parent[b] = a;
			rank[a] += rank[b];
			rank[b]=0;
		}
	}

	private static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a]=find(parent[a]);
	}
}
