import java.util.*;

class Solution {
    static int N;
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        N = computers[0].length;
        visited = new boolean[N];
        int answer = 0;
        
        for(int i = 0; i < N; i++){
            if(!visited[i]){
                dfs(i, computers);
                answer++;
            }
        }        
        return answer;
    }
    void dfs(int r, int[][] computers){
        visited[r] = true;
        for(int i = 0; i < N; i++){
            if(!visited[i] && computers[r][i]==1){
                dfs(i,computers);
            }
        }
    }
}