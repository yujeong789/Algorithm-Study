package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class 하노이탑이동순서 {
	static int N;
	static int min;
	static Stack<Integer> stack1 = new Stack<>();
	static Stack<Integer> stack2 = new Stack<>();
	static Stack<Integer> stack3 = new Stack<>();
	static StringBuilder aws = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		for(int i = N; i >= 1; i--) {
			stack1.add(i);
		}
		
		min = Integer.MAX_VALUE;
		even(0, 1, 0, stack1, stack2, stack3, sb);
		System.out.println(min);
	}

	private static boolean even(int cnt, int now, int prev, Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3, StringBuilder sb) {

		// 다 이동했으면 종료
		if(stack3.size()==N) {
			if(cnt < min) {
				min = cnt;
				return true;
			}
			return false;
		}
		// 이동횟수가 많으면 가지치기
		if(cnt>min) {
			return false; 
		}
		if(cnt>Math.pow(2, N)) {
			return false;
		}
		//처음상태로 돌아오면 가지치기
		if(cnt>0 && stack1.size()==N) {
			return false;
		}
		
		// 재귀호출
		if(stack1.size()>0) {
			if(stack3.size()==0 || stack1.peek()<stack3.peek()) {
				Stack<Integer> tmp1 = (Stack<Integer>) stack1.clone();
				Stack<Integer> tmp2 = (Stack<Integer>) stack2.clone();
				Stack<Integer> tmp3 = (Stack<Integer>) stack3.clone();
				int tmp = tmp1.pop();
				tmp3.add(tmp);
				even(cnt+1, 3, 1, tmp1, tmp2, tmp3, sb.append("1 3\n"));
			}
			if(stack2.size()==0 || stack1.peek()<stack2.peek()) {
				Stack<Integer> tmp1 = (Stack<Integer>) stack1.clone();
				Stack<Integer> tmp2 = (Stack<Integer>) stack2.clone();
				Stack<Integer> tmp3 = (Stack<Integer>) stack3.clone();
				int tmp = tmp1.pop();
				tmp2.add(tmp);
				even(cnt+1, 2, 1, tmp1, tmp2, tmp3, sb.append("1 2\n"));
			}
		}
		if(stack2.size()>0) {
			if(stack3.size()==0 || stack2.peek()<stack3.peek()) {
				Stack<Integer> tmp1 = (Stack<Integer>) stack1.clone();
				Stack<Integer> tmp2 = (Stack<Integer>) stack2.clone();
				Stack<Integer> tmp3 = (Stack<Integer>) stack3.clone();
				int tmp = tmp2.pop();
				tmp3.add(tmp);
				even(cnt+1, 3, 2, tmp1, tmp2, tmp3, sb.append("2 3\n"));
			}
			if(stack1.size()==0 || stack2.peek()<stack1.peek()) {
				Stack<Integer> tmp1 = (Stack<Integer>) stack1.clone();
				Stack<Integer> tmp2 = (Stack<Integer>) stack2.clone();
				Stack<Integer> tmp3 = (Stack<Integer>) stack3.clone();
				int tmp = tmp2.pop();
				tmp1.add(tmp);
				even(cnt+1, 1, 2, tmp1, tmp2, tmp3, sb.append("2 1\n"));
			}
		}
		if(stack3.size()>0) {
			if(stack2.size()==0 || stack3.peek()<stack2.peek()) {
				Stack<Integer> tmp1 = (Stack<Integer>) stack1.clone();
				Stack<Integer> tmp2 = (Stack<Integer>) stack2.clone();
				Stack<Integer> tmp3 = (Stack<Integer>) stack3.clone();
				int tmp = tmp3.pop();
				tmp2.add(tmp);
				even(cnt+1, 2, 3, tmp1, tmp2, tmp3, sb.append("3 2\n"));
			}
			if(stack1.size()==0 || stack3.peek()<stack1.peek()) {
				Stack<Integer> tmp1 = (Stack<Integer>) stack1.clone();
				Stack<Integer> tmp2 = (Stack<Integer>) stack2.clone();
				Stack<Integer> tmp3 = (Stack<Integer>) stack3.clone();
				int tmp = tmp3.pop();
				tmp1.add(tmp);
				even(cnt+1, 1, 3, tmp1, tmp2, tmp3, sb.append("3 1\n"));
			}
		}
		return false;
	}
}
