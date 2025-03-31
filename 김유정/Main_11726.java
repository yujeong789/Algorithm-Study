import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        arr[0] = 1;
        arr[1] = 1;
        for(int i = 2; i <= N; i++) {
        	arr[i] = (arr[i-1] + arr[i-2])%10007;
        }
        System.out.println(arr[N]);

	}
}
