import java.util.*;
import java.io.*;

public class 점심식사시간 {
	static class Person {
		int r;
		int c;
		int i;
		int j;
		int time;
		Person(int r, int c, int i, int j, int time){
			this.r = r;
			this.c = c;
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}
	static class Stair {
		int r;
		int c;
		Stair(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int T, N, result;
	static int[][] map;
	static List<Person> people;
	static List<Stair> stairs;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			people = new LinkedList<>();
			stairs = new LinkedList<>();
			result = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						people.add(new Person(i,j,-1,-1,0));
					}else if(map[i][j] > 1) {
						stairs.add(new Stair(i,j));
					}
				}
			}
			//전체소요시간, 개단개수, 사람수
			dfs(0, people.size(), people);
			System.out.println("#"+t+" "+result);
		}
	}

	private static void dfs(int cnt, int total, List<Person> people) {

		if(cnt==total) {
			escape(people);
			return;
		}
		for(Stair s : stairs) {
			people.get(cnt).i = s.r;
			people.get(cnt).j = s.c;
			dfs(cnt+1, total, people);
			people.get(cnt).i = -1;
			people.get(cnt).j = -1;
		}
	}

	private static void escape(List<Person> people) {
//		for(Person p : people) {
//			System.out.print(p.r+" "+p.c+" "+p.i+" "+p.j+" "+p.time+" // ");
//		}System.out.println();
		PriorityQueue<Integer> pq1 = new PriorityQueue<>();
		PriorityQueue<Integer> pq2 = new PriorityQueue<>();
		Stair stair1 = stairs.get(0);
		Stair stair2 = stairs.get(1);
		
		for(Person p : people) {
			p.time = Math.abs(p.r-p.i) + Math.abs(p.c-p.j);
			if(p.i==stair1.r && p.j==stair1.c){
				pq1.offer(p.time);				
			}else {
				pq2.offer(p.time);
			}
		}
		
		
		int time1 = caculate(map[stair1.r][stair1.c], pq1);
		int time2 = caculate(map[stair2.r][stair2.c], pq2);
		
		result = Math.min(result, Math.max(time1, time2));
	}

	private static int caculate(int stairLength, PriorityQueue<Integer> pq) {
		if(pq.isEmpty()) return 0;
		
		List<Integer> onStair = new ArrayList<>();
		int currentTime = 0;
		
		while(!pq.isEmpty() || !onStair.isEmpty()) {
			// 계단을 다 내려간 경우 삭제
			for(int i = 0; i < onStair.size(); i++) {
				if(onStair.get(i)>=currentTime) onStair.remove(i);
			}
			
			// 계단에 추가
			while(!pq.isEmpty() && onStair.size()<3) {
				int arrivalTime = pq.poll();
				int startTime = Math.max(currentTime, arrivalTime);
				onStair.add(startTime+1+stairLength);
			}
			
			if(!onStair.isEmpty()) {
				currentTime = Collections.min(onStair);
			}else if(!pq.isEmpty()) {
				currentTime = pq.peek();
			}
			
		}
			
		return currentTime;
	}
}
