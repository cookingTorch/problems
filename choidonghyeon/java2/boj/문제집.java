import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제집 {

	/**
	 * 위상 정렬?
	 * BFS-PriorityQueue 사용
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] inCount = new int[N + 1];
		ArrayList<Integer>[] grp = new ArrayList[N + 1];
		PriorityQueue<Integer> q = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			if (grp[A] == null) {
				grp[A] = new ArrayList<>();
			}
			grp[A].add(B);
			inCount[B] += 1;
		}

		for (int i = 1; i < N + 1; i++) {
			if (inCount[i] == 0) {
				q.add(i);
			}
		}

		String[] answer = new String[N];
		int idx = 0;
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			answer[idx++] = curr.toString();

			if (grp[curr] == null) {
				continue;
			}

			for (Integer next : grp[curr]) {
				if (--inCount[next] == 0) {
					q.add(next);
				}
			}
		}

		System.out.println(String.join(" ",answer));
	}
}
