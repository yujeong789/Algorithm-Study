import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minque = new PriorityQueue<>();
        PriorityQueue<Integer> maxque = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        int N = operations.length;
        for(String s : operations){
            if(s.split(" ")[0].equals("I")){ // 삽입
                int num = Integer.parseInt(s.split(" ")[1]);
                minque.offer(num);;
                maxque.offer(num);
                
            }else { // 삭제
                if(s.split(" ")[1].equals("1") && !maxque.isEmpty()){ // 최댓값 삭제
                    minque.remove(maxque.poll());
                    
                }else if(s.split(" ")[1].equals("-1") && !minque.isEmpty()){ //최솟값 삭제
                    maxque.remove(minque.poll());
                }
                
            }
        }
        
        int max = maxque.isEmpty()? 0 : maxque.poll();
        int min = minque.isEmpty()? 0 : minque.poll();
        int[] answer = new int[] {max, min};
        return answer;
    }
}