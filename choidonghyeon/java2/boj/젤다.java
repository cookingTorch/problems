import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 젤다 {
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
		int N;
		int cnt = 1;
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			System.out.println("Problem " + (cnt++) + ": " + solve(N));
		}
	}

	private static int solve(int n) throws IOException {
		int[][] grp = new int[n][n];
		updateGraph(grp, n);
		return dijkstra(grp, n);
	}

	private static int dijkstra(int[][] grp, int n) {
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		PriorityQueue<State> q = new PriorityQueue<>(Comparator.comparingInt(state -> state.amount));
		q.offer(new State(0, 0, grp[0][0]));
		dp[0][0] = grp[0][0];

		while (!q.isEmpty()) {
			State curr = q.poll();

			if (curr.y == n - 1 && curr.x == n - 1) {
				return curr.amount;
			}

			if (dp[curr.y][curr.x] < curr.amount) {
				continue;
			}

			for (int[] d : delta) {
				int dy = d[0] + curr.y;
				int dx = d[1] + curr.x;

				if (isMoveable(n, dy, dx) && dp[dy][dx] > curr.amount + grp[dy][dx]) {
					dp[dy][dx] = curr.amount + grp[dy][dx];
					q.offer(new State(dy, dx, dp[dy][dx]));
				}
			}
		}
		return dp[n - 1][n - 1];
	}

	private static boolean isMoveable(int n, int dy, int dx) {
		return 0 <= dy && 0 <= dx && dy < n && dx < n;
	}

	private static void updateGraph(int[][] grp, int n) throws IOException {
		for (int y = 0; y < n; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				grp[y][x] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
