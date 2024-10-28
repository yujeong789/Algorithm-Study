import java.util.*;
import java.io.*;

public class Main5430 {

	static int T;
	static char[] p;
	static int N;
	static String[] num;	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {

			LinkedList<String> deque = new LinkedList<>();
			
			p = br.readLine().toCharArray();
			N = Integer.parseInt(br.readLine());
			num = br.readLine().replace("[","").replace("]", "").split(",");
			
			for(int i = 0; i < num.length; i++) {
				if(num[i]=="") continue;
				deque.offerLast(num[i]);
			}
			boolean isError = false;
			boolean isReverse = false;
			for(int i = 0; i < p.length; i++) {
				if(p[i]=='R') {
					isReverse = !isReverse;
				}else if(p[i]=='D') {
//					System.out.println("삭제" +deque);
					if(deque.size()==0) {
						isError=true;
						break;
					}
					if(!isReverse) {
						deque.removeFirst();
					}else {
						deque.removeLast();
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			if(isError) System.out.println("error");
			else if(isReverse) {
				sb.append("[");
				int size = deque.size();
				for(int s = 0; s < size; s++) {
					sb.append(deque.removeLast());
					if(s==size-1) continue;
					sb.append(",");
				}
				sb.append("]");
				System.out.println(sb);
			}
			else {
				sb.append("[");
				int size = deque.size();
				for(int s = 0; s < size; s++) {
					sb.append(deque.removeFirst());
					if(s==size-1) continue;
					sb.append(",");
				}
				sb.append("]");
				System.out.println(sb);
			}

		}
	}

	
}

/*
4
RDD
4
[1,2,3,4]
DD
1
[42]
RRD
6
[1,1,2,3,5,8]
D
0
[]
 
 */