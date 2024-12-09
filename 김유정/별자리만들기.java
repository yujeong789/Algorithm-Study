import java.util.*;
import java.io.*;

public class 별자리만들기 {
	static class Dist{
		double r;
		double c;
		Dist(double r, double c){
			this.r = r;
			this.c = c;
		}
	}
	static class Node{
		int a;
		int b;
		double c;
		Node(int a, int b, double c){
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	static int N;
	static double result;
	static int[] parent, rank;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		rank = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
//		System.out.println(Arrays.toString(parent));
		List<Dist> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double r = Double.parseDouble(st.nextToken());
			double c = Double.parseDouble(st.nextToken());
			list.add(new Dist(r, c));
		}
		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->Double.compare(o1.c, o2.c));
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				double r1 = list.get(i).r;
				double c1 = list.get(i).c;
				double r2 = list.get(j).r;
				double c2 = list.get(j).c;
				double dist = Math.sqrt(Math.pow(r1-r2, 2)+Math.pow(c1-c2, 2));
				pq.add(new Node(i+1,j+1,dist));
			}
		}
		result = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			unoin(now.a, now.b, now.c);
		}
		System.out.println(result);
//		System.out.println(Arrays.toString(parent));
	}

	private static void unoin(int a, int b, double c) {
		int pa = find(a);
		int pb = find(b);
		if(pa==pb) return;
		if(rank[pa] > rank[pb]) {
			rank[pa]+=rank[pb];
			parent[pb] = parent[pa];
		}else {
			rank[pb]+=rank[pa];
			parent[pa] = parent[pb];
		}
		result += c;
		return;
		
	}

	private static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
	}
}
