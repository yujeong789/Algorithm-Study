import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
		int arrive;
		int check;
		
		Node(int arrive, int check){
			this.arrive = arrive;
			this.check = check;
		}
		@Override
		public int compareTo(Node o) {
			return this.arrive - o.arrive;
		}
	}
    static int N;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int arrive = Integer.parseInt(st.nextToken());
        	int check = Integer.parseInt(st.nextToken());
        	pq.offer(new Node(arrive, check));
        }
        
        int time = 0;
        for(int i = 0 ; i < N; i++) {
        	Node now = pq.poll();
        	if(time < now.arrive) time = now.arrive;
        	time += now.check;
        }
        
        System.out.println(time);
    }
}
