import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int[][] sum = new int[triangle.length][triangle.length];
    
        for(int i = 0; i < triangle.length; i++){
            sum[triangle.length-1][i] = triangle[triangle.length-1][i];
        
        }
        for(int i = triangle.length-1; i >= 1; i--){
            for(int j = 0; j < i; j++){
                sum[i-1][j] = triangle[i-1][j] + Math.max(sum[i][j], sum[i][j+1]);
            }    
        }

        int answer = sum[0][0];
        return answer;
    }
}