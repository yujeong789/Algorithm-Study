import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {
    static int N, min;
    static boolean[][] visited;
    static int[] dr = {-2, -2, 0, 0, 2, 2};
    static int[] dc = {-1, 1, -2, 2, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;
        bfs(r1, r2, c1, c2);
        if(min==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static void bfs(int r1, int r2, int c1, int c2) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {r1, c1, 0});

        while (!que.isEmpty()){
            int[] curr = que.poll();
            int r = curr[0];
            int c = curr[1];
            int cnt = curr[2];

            if(r==r2 && c==c2){
                min = Math.min(min, cnt);
                return;
            }

            for(int d = 0; d < 6; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(!check(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                que.offer(new int[] {nr,nc,cnt+1});
            }
        }

    }

    static boolean check(int r, int c){
        return r>=0 && r<N && c>=0 && c<N;
    }
}