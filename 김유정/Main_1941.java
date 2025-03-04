import java.io.*;
import java.util.*;

public class Main {
    static int N = 5;
    static char[][] map = new char[N][N];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int answer = 0;
    static boolean[] selected = new boolean[N*N]; // 25개 위치에서 7개 선택
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받기
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        // 25개 중 7개 선택하는 조합 (조합 후 조건 체크)
        combination(0, 0);
        
        System.out.println(answer);
    }
    
    // idx: 현재 위치 인덱스(0~24), cnt: 현재까지 선택한 칸의 개수
    private static void combination(int idx, int cnt) {
        if(cnt == 7) {
            // 7개를 선택했을 때 조건 체크
            // 1. 이다솜파('S')가 4명 이상인지 확인
            // 2. 7명이 모두 인접해 있는지 확인
            if(checkSomCount() && checkAdjacent()) {
                answer++;
            }
            return;
        }
        
        if(idx >= N*N) return;
        
        // 현재 위치 선택
        selected[idx] = true;
        combination(idx + 1, cnt + 1);
        
        // 현재 위치 선택하지 않음
        selected[idx] = false;
        combination(idx + 1, cnt);
    }
    
    // 이다솜파('S')가 4명 이상인지 확인
    private static boolean checkSomCount() {
        int somCount = 0;
        for(int i = 0; i < N*N; i++) {
            if(selected[i]) {
                int r = i / N;
                int c = i % N;
                if(map[r][c] == 'S') {
                    somCount++;
                }
            }
        }
        return somCount >= 4;
    }
    
    // 7명이 모두 인접해 있는지 확인 (BFS 이용)
    private static boolean checkAdjacent() {
        int[][] selectedMap = new int[N][N];
        int startR = -1, startC = -1;
        
        // 선택된 위치 표시
        for(int i = 0; i < N*N; i++) {
            if(selected[i]) {
                int r = i / N;
                int c = i % N;
                selectedMap[r][c] = 1;
                
                // 시작점 저장
                if(startR == -1) {
                    startR = r;
                    startC = c;
                }
            }
        }
        
        // BFS로 연결성 확인
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;
        int count = 1; // 방문한 칸 수
        
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr >= 0 && nr < N && nc >= 0 && nc < N 
                   && selectedMap[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                    count++;
                }
            }
        }
        
        // 7개가 모두 연결되어 있는지 확인
        return count == 7;
    }
}
