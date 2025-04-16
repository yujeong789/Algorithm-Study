import java.io.*;
import java.util.*;

public class Main {
    static int N, M, CCheese;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        CCheese = 0;
        int time = 0;
        int cheese = countCheese();

        while(cheese > 0) {
            CCheese = cheese; // 녹기 전 치즈 개수 저장
            meltCheese();
            time++;
            cheese = countCheese();
        }

        System.out.println(time);
        System.out.println(CCheese);
    }

    private static void meltCheese() {
        visited = new boolean[N][M];
        boolean[][] willMelt = new boolean[N][M]; // 녹을 치즈 표시
        Queue<int[]> queue = new LinkedList<>();
        
        // 가장자리부터 시작하는 BFS (항상 외부 공기만 시작점)
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        // 외부 공기 BFS
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
                
                // 치즈를 만나면 녹을 예정으로 표시
                if(map[nr][nc] == 1) {
                    willMelt[nr][nc] = true;
                    visited[nr][nc] = true;
                } else { // 공기면 계속 탐색
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        // 표시된 치즈 녹이기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(willMelt[i][j]) {
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int countCheese() {
        int count = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
