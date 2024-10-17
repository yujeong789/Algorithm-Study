import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        char[] cc = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        
        for(char c : cc){
            if(c=='('){
                stack.add(c);
                
            }else {
                if(stack.size() > 0) {
                    char tmp = stack.pop();
                }else{
                    answer = false;
                    break;
                }
                
            }
        }
        if(stack.size()>0) answer = false;

        return answer;
    }
}