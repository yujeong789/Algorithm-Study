import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 거짓말 {

	static int N,M;//사람의수, 파티의 수
	static int truth; // 진실을 아는 사람들의 수
	static boolean[] truthPeople; // 진실을 아는 사람들
	static int party; // 파티에 오는 사람들의 수
	static List<Integer>[] partyPeople; // 파티에 오는 사람들
	static Queue<Integer> que;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //사람의수
		M = Integer.parseInt(st.nextToken()); //파티의 수
		
		// 진실을 아는 사람들
		st = new StringTokenizer(br.readLine());
		truth = Integer.parseInt(st.nextToken());
		truthPeople = new boolean[N+1];
		que = new LinkedList<>(); // 진실을 아는 사람들, bfs돌릴 큐
		for(int t = 0; t < truth; t++) {
			int tmp = Integer.parseInt(st.nextToken());
			truthPeople[tmp] = true;
			que.offer(tmp);
		}
		
		// 파티 횟수
		partyPeople = new ArrayList[M];
		for(int m = 0; m < M; m++) {
			partyPeople[m] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			// 파티 참여자들
			st = new StringTokenizer(br.readLine());
			party = Integer.parseInt(st.nextToken());
			for(int p = 0; p < party; p++) {
				partyPeople[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		// 입력--------------
		// 연산--------------
//		System.out.println(Arrays.toString(truthPeople));
//		for(int m = 0; m < M; m++) {
//			System.out.println(partyPeople[m]);
//		}
		
		// 진실을 아는 사람들을 큐에 담고 돌린다
		Set<Integer> set = new HashSet<>(); // 진실을 아는 파티의 집합
		while(!que.isEmpty()) {
			int curr = que.poll();
			for(int m = 0; m < M; m++) { // 파티 갯수만큼 돈다
				if(partyPeople[m].contains(curr)) { //진실을 아는 사람이 포함된 파티라면
					set.add(m);
					for(int p : partyPeople[m]) {
						if(!truthPeople[p]) { // 진실을 몰랐던 사람들은 
							truthPeople[p] = true; // 전부 진실을 알게 된다
							que.offer(p); // 진실을 몰랐다가 안 사람들 큐에 추가
						}
					}
				}
			}
		}
		System.out.println(M-set.size()); // 전체파티 - 진실을 아는 파티의 수
		
	}
}
