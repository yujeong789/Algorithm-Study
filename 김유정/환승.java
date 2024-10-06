import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 모든 역들을 링크드 리스트로 받아서 서로 연결관계 확인할 수 있도록 함
// N번역에서 연결된 역들을 dfs로 탐색 -> 시간초과

// 
public class 환승 {
	static int N,K,M;
	static List<Integer>[] station;
	static List<Integer>[] hypertube;
	static boolean[] visited;
	static boolean[] visitedStation;
	static boolean[] visitedHypertube;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //역의수
		K = Integer.parseInt(st.nextToken()); //하이퍼튜브가 연결하는 역의 개수
		M = Integer.parseInt(st.nextToken()); //하이퍼튜브의개수
		station = new ArrayList[N+1];
		hypertube = new ArrayList[M+1];
		visitedStation = new boolean[N+1];
		visitedHypertube = new boolean[M+1];
		for(int i = 0; i < N+1; i++) { //station초기화
			station[i] = new ArrayList<>();
		}
		for(int i = 0; i < M+1; i++) { //hypertube초기화
			hypertube[i] = new ArrayList<>();
		}
		for(int m = 1; m < M+1; m++) {
			// 들어오는 수를 임시로 저장할 배열
			int[] num = new int[K]; 
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < K; k++) {
				num[k] = Integer.parseInt(st.nextToken());
			}
			// station : hypertube의 꼴로 저장
			for(int n : num) {
				station[n].add(m);
			}
			// hypertube : station1, station2, station3...의 꼴로 저장
			for(int n : num) {
				hypertube[m].add(n);
			}
		}
		// 입력--------------------------
		// 연산--------------------------
		int result = bfs(1, 1);
		System.out.println(result);
	}

	private static int bfs(int cnt, int start) {
		
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {cnt, start});
		visitedStation[start] = true; // 하나의 역을 중복 지나지 않기 위해서
		visitedHypertube[station[start].get(0)] = true; // 하이퍼 튜브 중복 방지
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int currentCnt = curr[0];
			int currentStart = curr[1];

			if(currentStart==N) { // N번 역에 도착했다면 현재의 횟수 반환
				return currentCnt; // 현재가 가장 작은 값이므로 min과의 비교 필요x
			}
			
			for(int tube : station[currentStart]) {
				
				if(visitedHypertube[tube]) continue;
				visitedHypertube[tube] = true;
				
				// 현재 역과 연결된 곳들 탐색
				for(int nextStation : hypertube[tube]) {
					if(visitedStation[nextStation]) continue;
					visitedStation[nextStation] = true;
					que.offer(new int[] {currentCnt+1, nextStation});
				}
			}
		}
		return -1;
	}

	
}
