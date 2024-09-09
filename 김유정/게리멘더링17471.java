import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리멘더링17471 {
	static int N;//구역의개수
	static int[] people; // 구역별 인구수
	static int[][] map;
	static boolean[] visited;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		people = new int[N+1];
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		} //각지역 인구수
		for(int r = 1; r < N+1; r++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int k = 0; k < n; k++) {
				int c = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
				map[c][r] = 1;
			}
		} //인접한구역 배열로 입력
		
		//입력--
		//연산--
		min=1000000;
		powerset(0);
		System.out.println(min==1000000?-1:min);
		
	}

	static void powerset(int cnt) {
		if(cnt==N) {
//			System.out.println(Arrays.toString(visited));
			int conn = conn();
			min = Math.min(min, conn);
			return;
		}
		visited[cnt] = true;
		powerset(cnt+1);
		visited[cnt] = false;
		powerset(cnt+1);
	}

	private static int conn() {
		List<Integer> trueList = new ArrayList<>();
		List<Integer> falseList = new ArrayList<>();
		for(int i = 1; i < N+1; i++) {
			if(visited[i]) trueList.add(i);
			else falseList.add(i);
		} // true와 false별로 구역 분리
		
//		System.out.println(trueList);
//		System.out.println(falseList);
//		System.out.println();
		
		//연결확인
		boolean[] vv = new boolean[N+1];
		bfs(trueList, vv);
		bfs(falseList, vv);
		
		// 전부 연결된게 확실하다면
		if(checkConn(vv)) {
			int a=0;
			for(int t : trueList) {
				a += people[t];
			}
			int b=0;
			for(int f : falseList) {
				b += people[f];
			}
			return Math.abs(a-b); //인구수차이 구하기
		}
		return 10000000; //대충 엄청 큰 값 보내기
	}

	//연결됏는지 최종확인
	private static boolean checkConn(boolean[] vv) {
		for(int i = 1; i < N+1; i++) {
			if(!vv[i]) return false; 
		}
		return true;
	}

	// 연결확인
	private static void bfs(List<Integer> list, boolean[] vv) {
		Queue<Integer> que = new LinkedList<>();
		
		if(list.size()==0) return;
		que.offer(list.get(0));
		vv[list.get(0)] = true;
		while(!que.isEmpty()) {
			int c = que.poll();
			for(int i = 0; i < list.size(); i++) {
				if(vv[list.get(i)]) continue;
				if(map[c][list.get(i)]==1) {
					que.add(list.get(i));
					vv[list.get(i)] = true;
				}
			}
		}
		
	}
	
}

/*/
 * arrayList 사용해서 t/f 분류
 * 
 */

