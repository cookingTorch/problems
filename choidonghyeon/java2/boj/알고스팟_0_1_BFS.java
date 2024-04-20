import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 알고스팟_0_1_BFS {
	static final int[][] delta = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class State {
		int y;
		int x;
		int amount;

		public State(int y, int x, int amount) {
			this.y = y;
			this.x = x;
			this.amount = amount;
		}
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		char[][] grp = new char[n][m];
		updateGraph(grp, n, m);
		System.out.println(bfs(grp, n, m));
	}

	private static int bfs(char[][] grp, int n, int m) {
		boolean[][] dp = new boolean[n][m];

		Deque<State> q = new ArrayDeque<>();
		q.offer(new State(0, 0, 0));

		dp[0][0] = true;
		while (!q.isEmpty()) {
			State curr = q.poll();
			if (curr.y == n - 1 && curr.x == m - 1) {
				return curr.amount;
			}

			for (int[] d : delta) {
				int dy = d[0] + curr.y;
				int dx = d[1] + curr.x;

				if (isMoveable(n, m, dy, dx) && !dp[dy][dx]) {
					dp[dy][dx] = true;
					if (grp[dy][dx] == '0') {
						q.offerFirst(new State(dy, dx, curr.amount));
					} else {
						q.offerLast(new State(dy, dx, curr.amount + 1));
					}
				}
			}
		}
		return -1;
	}

	private static boolean isMoveable(int n, int m, int dy, int dx) {
		return 0 <= dy && 0 <= dx && dy < n && dx < m;
	}

	private static void updateGraph(char[][] grp, int n, int m) throws IOException {
		for (int y = 0; y < n; y++) {
			grp[y] = br.readLine().toCharArray();
		}
	}
}
