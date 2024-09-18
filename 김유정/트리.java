package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 트리2 {
	static int N;
	static int[] nums;
	static boolean[] visited;
	static int erase;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[50];
		visited = new boolean[N]; //지워진 노드인지 확인
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		erase = Integer.parseInt(br.readLine());
		
		// 지워진 노드는 -10으로 표시
		nums[erase]=-10;
		for(int i = erase; i< N; i++) {
			if(nums[i]==-10) { // 지워진 노드를 발견하면
				for(int j = i; j < N; j++) { // 해당 노드의 인덱스를 값으로 가진 노드 삭제
					if(nums[j]==i) nums[j]=-10;
				}
			}
		}

		// 리프노드 개수 세기 위한 연산
		for(int i = N-1; i >=0; i--) {
			if(nums[i]==-10) { // 이미 지워진 노드는 true;
				visited[i]=true;
				continue;
			}
			if(visited[i]) continue; // 이미 지워진 노드면 연산x
			int tmp = nums[i]; // 지울 노드의 부모 (자식이 있으면 리프노드가 아니므로)
			while(true) {
				if(tmp==-1) break; // 루트노드까지 가면 끝
				visited[tmp]=true; // 부모 노드 지우기
				tmp=nums[tmp]; // 또 그 부모노드 찾기
			}
		}

		int cnt=0;
		for(int i = 0; i < N; i++) {
			if(!visited[i]) cnt++; // 리프노드만 false로 남아있으므로
		}
		System.out.println(cnt);
	}
}
