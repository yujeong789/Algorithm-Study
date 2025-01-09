import java.util.*;
import java.io.*;

public class Main_10026 {
    static int N;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i = 0; i < N; i++){
            String tmp = br.readLine();
            for(int j = 0; j < tmp.length(); j++){
                map[i][j] = tmp.charAt(j);
            }
        }

        int area = 0;
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                area += countArea(i,j, map[i][j]);
            }
        }
        System.out.print(area+" ");

        area = 0;
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                area += countArea2(i,j, map[i][j]);
            }
        }
        System.out.print(area+" ");


    }

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    private static int countArea(int sr, int sc, char color) {
        if(visited[sr][sc]) return 0;
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {sr,sc});

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int r = curr[0];
            int c = curr[1];

            for(int d = 0; d < 4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(!check(nr,nc)) continue;
                if(map[nr][nc] != color) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                que.offer(new int[] {nr,nc});
            }
        }
        return 1;
    }

    private static int countArea2(int sr, int sc, char color) {
        if(visited[sr][sc]) return 0;
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {sr,sc});

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int r = curr[0];
            int c = curr[1];

            for(int d = 0; d < 4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(!check(nr,nc)) continue;
                if(color=='R' && map[nr][nc]=='B') continue;
                if(color=='G' && map[nr][nc]=='B') continue;
                if(color=='B' && map[nr][nc]!='B') continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                que.offer(new int[] {nr,nc});
            }
        }
        return 1;
    }

    static boolean check(int r, int c){
        return r>=0 && r<N && c>=0 && c<N;
    }
}