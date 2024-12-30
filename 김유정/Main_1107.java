import java.util.*;
import java.io.*;

public class Main {
    static int N, M, min;
    static boolean[] broken;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        broken = new boolean[10];

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int n = Integer.parseInt(st.nextToken());
                broken[n] = true;
            }
        }
        
        min = Math.abs(N - 100); // +/-로만 이동할 경우
        if (M < 10) { // 누를 수 있는 버튼이 있을 때만 탐색
            findMin(0, 0);
        }
        System.out.println(min);
    }

    private static void findMin(int num, int cnt) {
        if (cnt > 6) return;

        int press = Math.abs(N - num);
        min = Math.min(min, press + cnt);

        for (int i = 0; i < 10; i++) {
            if (!broken[i]) {
                findMin(num * 10 + i, cnt + 1);
            }
        }
    }
}