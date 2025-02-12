import java.util.*;
import java.io.*;

public class Main {

    static int F,S,G,U,D;
    static int[] dd;

    public static void main(String[]args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken()); //전체층
        S = Integer.parseInt(st.nextToken()); //현재층
        G = Integer.parseInt(st.nextToken()); //목표층
        U = Integer.parseInt(st.nextToken()); //위로
        D = Integer.parseInt(st.nextToken()); //아래로

        dd = new int[]{U, -1*D};
        int result = bfs(S,G);
        System.out.println(result==-1?"use the stairs":result);

    }

    private static int bfs(int S, int G) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {S, 0});
        boolean[] visited = new boolean[F+1];
        visited[S] = true;

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int stair = curr[0];
            int cnt = curr[1];

            if(stair==G)
                return cnt;

            for(int d = 0; d < 2; d++){
                int nStair = stair+dd[d];
                if(!(nStair>=1 && nStair<=F)) continue;
                if(visited[nStair]) continue;
                visited[nStair] = true;
                que.offer(new int[] {nStair, cnt+1});
            }
        }
        return -1;
    }
}
