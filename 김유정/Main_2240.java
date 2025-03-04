import java.io.*;
import java.util.*;

public class Main {
	static int T, W;
	static int[][][] arr;
	static boolean[][] cherry;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W+1][2+1][T+1];
        cherry = new boolean[2+1][T+1];
        
        int tree;
        for(int t = 1; t <= T; t++) {
        	tree = Integer.parseInt(br.readLine());
        	if(tree==1) {
        		arr[0][tree][t] = 1;
        		cherry[tree][t] = true;
        	}
        	else {
        		arr[1][tree][t] = 1;
        		cherry[tree][t] = true;
        	}
        }
        
        //0번 이동할 때
        for(int t = 1; t <= T; t++) {
        	arr[0][1][t] += arr[0][1][t-1];
        }
        
        //1번 이동할 때
        for(int t = 1; t <= T; t++) {
        	arr[1][2][t] += Math.max(arr[0][1][t-1], arr[1][2][t-1]);
        }
        
        if(W==1) {
        	System.out.println(Math.max(arr[0][1][T], arr[1][2][T]));
        	return;
        }
        
        //1번 이상 이동할 때
        for(int w = 2; w <= W; w++) {
        	for(int t = 1; t <= T; t++) {
        		// 1번나무
        		arr[w][1][t] = (cherry[1][t]?1:0) + Math.max(arr[w-1][2][t-1], arr[w][1][t-1]);
        		
        		// 2번나무
        		arr[w][2][t] = (cherry[2][t]?1:0) + Math.max(arr[w-1][1][t-1], arr[w][2][t-1]);

        	}
        }
        
//        for(int w = 0; w <= W; w++) {
//        	System.out.println("w: "+w+" "+Arrays.toString(arr[w][1]));
//        	System.out.println("w: "+w+" "+Arrays.toString(arr[w][2]));
//        	System.out.println();
//        }
        System.out.println(Math.max(arr[W][1][T], arr[W][2][T]));
	}
}
