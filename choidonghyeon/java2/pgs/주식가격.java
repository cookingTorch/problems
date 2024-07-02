import java.util.*;

class 주식가격 {
	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		Deque<int[]> stack = new ArrayDeque<>();

		for(int i = 0; i < prices.length; i++) {
			while(!stack.isEmpty() && stack.peek()[1] > prices[i]) {
				int[] state= stack.pop();
				answer[state[0]] = i - state[0];
			}
			stack.push(new int[]{i,prices[i]});
		}


		while(!stack.isEmpty()) {
			int[] state= stack.pop();
			answer[state[0]] = prices.length - state[0] - 1;
		}
		return answer;
	}
}
