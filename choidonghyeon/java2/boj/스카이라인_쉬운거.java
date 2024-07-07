import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 스카이라인_쉬운거 {

	/**
	 * 고도 낮아 졌을시 빌딩 + 1
	 */
	public static void main(String[] args) throws IOException {
		int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean zero = false;
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Integer x = Integer.valueOf(st.nextToken());
			Integer y = Integer.valueOf(st.nextToken());
			zero = y == 0 || zero;

			while (!stack.isEmpty() && stack.peek() > y) {
				stack.pop();
				answer++;
			}

			if (!stack.isEmpty() && stack.peek().equals(y)) {
				continue;
			}
			stack.push(y);
		}

		answer += stack.size() - (zero ? 1 : 0);
		System.out.println(answer);
	}
}
