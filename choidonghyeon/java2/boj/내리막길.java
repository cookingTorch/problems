import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길 {
	static int M, N;
	static int[][] board;
	static int[][] dp;
	static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		board = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(dfs(0,0));
	}

	static int dfs(int y, int x) {
		if (y == M - 1 && x == N - 1) {
			return 1;
		}

		if (dp[y][x] == -1) {
			dp[y][x] = 0;
			for (int[] d : delta) {
				int ny = y + d[0];
				int nx = x + d[1];

				if (0 <= ny && ny < M && 0 <= nx && nx < N && board[y][x] > board[ny][nx] ) {
					dp[y][x] += dfs(ny,nx);
				}
			}
		}
		return dp[y][x];
	}
}
