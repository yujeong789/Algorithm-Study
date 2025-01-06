import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int a;
		int b;
		int c;
		Node(int a, int b, int c){
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	static int N, M, min, max;
	static PriorityQueue<Node> pq;
	static int[] parent;
	static int[] rank;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>((o1,o2)->(o1.c-o2.c));

        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	pq.add(new Node(a, b, c));
        }
        
        parent = new int[N+1];
        rank = new int[N+1];        
        for(int i = 1; i <= N; i++) {
        	parent[i] = i;
        	rank[i] = 1;
        }
        min = 0;
        max = 0;
        while(!pq.isEmpty()) {
        	Node now = pq.poll();
        	if(union(now.a, now.b)) {
        		System.out.println(now.c);
        		min+=now.c;
        		max = now.c;
        	}
        }
        
//        System.out.println(min-max);
	}

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		if(rank[a]>rank[b]) {
			rank[a] += rank[b];
			parent[b] = a;
		}else {
			rank[b] += rank[a];
			parent[a] = b;
		}
		return true;
	}

	private static int find(int a) {
		if(a==parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
}