import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class 소문난_칠공주 {
	static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static char[][] board = new char[5][5];
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			board[i] = br.readLine().toCharArray();
		}

		comb(new int[7], 0, 0, 0);
		System.out.println(answer);
	}

	private static void comb(int[] ints, int start, int currIdx, int totalCnt) {
		if (totalCnt == 7) {
			checkPossible(ints);
			return;
		}

		if (25 - start < 7 - totalCnt) {
			return;
		}

		ints[currIdx] = start;
		comb(ints, start + 1, currIdx + 1, totalCnt + 1);
		comb(ints, start + 1, currIdx, totalCnt);
	}

	private static void checkPossible(int[] ints) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[7];

		int totalCnt = 0;
		int sCnt = 0;
		q.offer(ints[0]);
		visited[0] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();
			totalCnt++;
			sCnt += board[curr / 5][curr % 5] == 'S' ? 1 : 0;

			if (totalCnt == 7) {
				break;
			}

			for (int[] d : delta) {
				int ny = curr / 5 + d[0];
				int nx = curr % 5 + d[1];
				int next = ny * 5 + nx;

				if (0 > ny || 0 > nx || ny >= 5 || nx >= 5) {
					continue;
				}

				for (int i = 1; i < 7; i++) {
					if (ints[i] == next && !visited[i]) {
						q.offer(next);
						visited[i] = true;
					}
				}
			}
		}

		if (totalCnt == 7 && sCnt >= 4) {
			answer++;
		}
	}
}
