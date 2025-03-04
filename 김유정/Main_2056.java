import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] list;
	static int[] arr, before;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        before = new int[N+1];
        list = new LinkedList[N+1];
        for(int i = 1; i <= N; i++) {
        	list[i] = new LinkedList<>();
        }
 
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i] = Integer.parseInt(st.nextToken());
        	int isNext = Integer.parseInt(st.nextToken());
        	if(isNext==0) continue;
        	for(int j = 0; j < isNext; j++) {
        		int next = Integer.parseInt(st.nextToken());
        		list[next].add(i);
        		before[i]++;
        	}
        }

        int time = calTime(arr, list);
        System.out.println(time);
        
    }

	private static int calTime(int[] arr, List<Integer>[] list) {
		Queue<int[]> que = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(before[i]==0) que.offer(new int[] {i,arr[i], 0});
		}
		
		int time = 0;
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int nowIdx = curr[0];
			int restTime = curr[1];
			int currentTime = curr[2];
			
			time = currentTime;
			
//			System.out.println("time: "+time+" nowIdx: "+nowIdx+" restTime: "+restTime+" currentTime: "+currentTime);
			if(restTime == 0) {
				for(int idx : list[nowIdx]) {
					before[idx]--;
					if(before[idx]==0) que.offer(new int[] {idx, arr[idx], currentTime});
				}
			}else if(restTime > 0) {
				time++;
				restTime--;
				currentTime++;
				que.offer(new int[] {nowIdx, restTime, currentTime});
			}
			
		}
		
		return time;
	}
}
