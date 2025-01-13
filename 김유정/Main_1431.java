import java.util.*;
import java.io.*;

public class Main {
	static class Node implements Comparable<Node>{
		String serialNum;
		int length;
		int sum;
		Node(String serialNum, int length, int sum){
			this.serialNum = serialNum;
			this.length = length;
			this.sum = sum;
		}
		@Override
		public int compareTo(Node o) {
			if(this.length != o.length) return this.length - o.length;
			if(this.sum != o.sum) return this.sum - o.sum;
			return this.serialNum.compareTo(o.serialNum);
		}
	}
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			String serial = br.readLine();
			int length = serial.length();
			int sum = 0;
			for(int j = 0; j < serial.length(); j++) {
				if(serial.charAt(j)-'0' >= 0 && serial.charAt(j)-'0'<=9) {
					sum += serial.charAt(j)-'0';
				}
			}
			pq.offer(new Node(serial, length, sum));
		}
		for(int i = 0; i < N; i++) {
			System.out.println(pq.poll().serialNum);
		}
	}
}
