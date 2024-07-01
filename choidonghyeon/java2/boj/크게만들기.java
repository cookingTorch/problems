import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 크게만들기 {

	/**
	 * N, K
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int finalLength = N - K;

		String numStr = br.readLine();
		Deque<Integer> dq = new ArrayDeque<>();

		for (char c : numStr.toCharArray()) {
			Integer num = c - 48;

			while (K >= 1 && !dq.isEmpty() && dq.peek() < num) {
				dq.pop();
				K--;
			}
			dq.push(num);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < finalLength; i++) {
			sb.append(dq.pollLast());
		}
		System.out.println(sb);
	}
}
