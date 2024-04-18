import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 알고스팟_다익스트라 {
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
		int[][] grp = new int[n][m];
		updateGraph(grp, n, m);
		System.out.println(dijkstra(grp, n, m));
	}

	private static int dijkstra(int[][] grp, int n, int m) {
		int[][] dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		PriorityQueue<State> q = new PriorityQueue<>(Comparator.comparingInt(state -> state.amount));
		q.offer(new State(0, 0, 0));
		dp[0][0] = 0;

		while (!q.isEmpty()) {
			State curr = q.poll();

			if (curr.y == n - 1 && curr.x == m - 1) {
				return curr.amount;
			}

			if (dp[curr.y][curr.x] < curr.amount) {
				continue;
			}

			for (int[] d : delta) {
				int dy = d[0] + curr.y;
				int dx = d[1] + curr.x;

				if (isMoveable(n, m, dy, dx) && dp[dy][dx] > curr.amount + grp[dy][dx]) {
					dp[dy][dx] = curr.amount + grp[dy][dx];
					q.offer(new State(dy, dx, dp[dy][dx]));
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	private static boolean isMoveable(int n,int m, int dy, int dx) {
		return 0 <= dy && 0 <= dx && dy < n && dx < m;
	}

	private static void updateGraph(int[][] grp, int n, int m) throws IOException {
		for (int y = 0; y < n; y++) {
			char[] charArray = br.readLine().toCharArray();
			for (int x = 0; x < m; x++) {
				grp[y][x] = charArray[x] - '0';
			}
		}
	}
}
