import java.util.*;
import java.io.*;

public class Main {
	
	static class Node implements Comparable<Node>{
		int coordinate;
		int type;
		Node(int coordinate, int type){
			this.coordinate = coordinate;
			this.type = type;
		}
		public int compareTo(Node o) {
			if(this.coordinate != o.coordinate) 
				return this.coordinate-o.coordinate;
			else
				return this.type-o.type;
		}
	}
	static int N;
	static Map<Integer, Integer> map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();

		int s = 0;
		int e = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			pq.offer(new Node(e,-1));
			pq.offer(new Node(s,1));
		}
		
		int size = pq.size();
		int cnt = 0;
		int max = 0;
		for(int i = 0; i < size; i++) {
			Node now = pq.poll();
//			System.out.println(now.coordinate+" "+now.type);
			if(now.type==1) cnt++;
			else cnt--;
			max = Math.max(cnt, max);
//			System.out.println("size: "+cnt);
		}
		System.out.println(max);

	}	
}



