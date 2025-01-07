import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {
    static int T, N, M, K;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];

            for(int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = -1;
            }

//            System.out.println();
//            for(int d = 0; d < N; d++){
//                System.out.println(Arrays.toString(map[d]));
//            }

            int cnt = 1;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j]!=-1) continue;;
                    floodfill(i, j, cnt++);

//                    for(int d = 0; d < N; d++){
//                        System.out.println(Arrays.toString(map[d]));
//                    }
                }
            }
            System.out.println(cnt-1);
        }
    }

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    private static void floodfill(int sx, int sy, int cnt) {
        if(map[sx][sy] != -1) return; // 배추가 없는 곳이면 탐색하지 않음

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {sx,sy});
        map[sx][sy] = cnt;

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int x = curr[0];
            int y = curr[1];

            for(int d = 0; d < 4; d++){
                int nx = x+dr[d];
                int ny = y+dc[d];

                if(!check(nx,ny)) continue;
                if(map[nx][ny] != -1) continue; // 배추가 있는 곳(-1)만 방문
                map[nx][ny] = cnt;
                que.offer(new int[] {nx,ny});
            }
        }
    }

    static boolean check(int x, int y){
        return x>=0 && x<N && y>=0 && y<M;
    }
}