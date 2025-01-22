import java.util.*;
import java.io.*;

public class Main4 {
	static int N;
	static int[][] map, ans;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        ans = new int[N][N];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        for(int k = 0; k < N; k++) {
        	for(int i = 0; i < N; i++) {
        		for(int j = 0; j < N; j++) {
        			if(map[i][k]==1 && map[k][j]==1) map[i][j]=1;
        		}
        	}
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		if(map[i][j]==1) sb.append("1").append(" ");
        		else sb.append("0").append(" ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }

}