import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
           set.add(Integer.parseInt(st.nextToken()));
        }
        int[] map = new int[set.size()];
        int idx = 0;
        for(int i : set){
            map[idx++] = i;
        }
        Arrays.sort(map);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.length; i++) {
            sb.append(map[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}