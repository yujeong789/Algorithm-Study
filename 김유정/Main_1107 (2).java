import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {
    static String target, push;
    static int N, M, now, min, ans;
    static boolean[] buttons;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        target = br.readLine();
        N = Integer.parseInt(target);
        M = Integer.parseInt(br.readLine());
        buttons = new boolean[10];
        if(M>0){
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++){
                buttons[Integer.parseInt(st.nextToken())] = true;
            }
        }
//        System.out.println(Arrays.toString(buttons));
        min = Math.abs(100-N);
        for(int i = 0; i < 999_999; i++) {
        	int len = possible(i);
        	if(len>=0) {
        		min = Math.min(min, len+Math.abs(N-i));
        	}
        	
        }
        System.out.println(min);
    }

	private static int possible(int i) {
		int cnt = 0;
		if(i==0) return buttons[0] ? -1 : 1;
		while(i>0) {
			int value = i%10;
			if(buttons[value]) return -1;
			i /= 10;
			cnt++;
		}
		return cnt;
	}

}