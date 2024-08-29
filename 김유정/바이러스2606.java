import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 바이러스2606 {
	static int computer, network, count;
	static boolean[] visited;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		computer = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		network = Integer.parseInt(br.readLine()); // 서로 연결되어 있는 컴퓨터 쌍의 수
		visited = new boolean[computer+1]; // 방문한 적 있는지 확인
		map = new int[computer+1][computer+1]; // 간선 확인 배열
		for(int i = 0; i < network; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
			map[c][r] = 1;
		}
		
		// bfs 탐색, 1번 컴퓨터부터 시작
		bfs(1);	
		
		// 감염된 컴퓨터 개수 탐색
		count = 0;
		// 1번컴퓨터에 의해 감염되므로 2번부터 탐색
		for(int i = 2; i < computer+1; i++) {
			if(visited[i]) count++;
		}
		System.out.println(count);
	}

	static void bfs(int v) {
		Queue<Integer> que = new LinkedList<>();
		visited[v] = true; // 해당 컴퓨터는 방문한 것
		que.offer(v); // 큐에 삽입
		
		while(!que.isEmpty()) { // 큐가 빌때까지 반복
			int s = que.poll(); // 큐의 첫번째 값
			for(int i = 1; i < computer+1; i++) {
				if(visited[i]) continue; //방문한적 있으면 패스
				if(map[s][i] == 1) { // 간선으로 연결되어 있으면 돈다
					que.offer(i); // 큐에 삽입
					visited[i] = true; // 해당 정점은 방문한 것
				}
			}
			
		}
	}
	
}
