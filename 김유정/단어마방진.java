import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 단어마방진 {

	static int L,N;
	static String[] map;
	static String[] tmp;
	static boolean[] visited;
	static List<String> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new String[N];
		tmp = new String[L];
		visited = new boolean[N];
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}

		dfs(0, tmp, visited);
		
		if(list.size()==0)System.out.println("NONE");
		else {
			Collections.sort(list);
			for(int i = 0; i < L*L; i+=L) {
				System.out.println(list.get(0).substring(i, i+L));
			}
		}
	}

	private static void dfs(int cnt, String[] tmp, boolean[] visited) {
		if(cnt==L) {
//			System.out.println(Arrays.toString(tmp));
//			System.out.println();
			char[][] test = new char[L][L];
			for(int i = 0; i < L; i++) {
				test[i] = tmp[i].toCharArray();
			}
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < L; j++) {
					if(test[i][j]!=test[j][i]) return;
				}
			}
			
			String result="";
			for(int i = 0; i < L; i++) {
				result += tmp[i];
			}
			list.add(result);
			return;
		}
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			tmp[cnt] = map[i];
			dfs(cnt+1, tmp, visited);
			tmp[cnt]=null;
			visited[i] = false;

		}
		
	}
}
	
