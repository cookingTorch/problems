package third.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 *
 * @author yongjunhong
 * @since  2024. 6. 21.
 */
public class boj_1520 {
	private static int[][] map;
	private static int[][] isVisited;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static int m, n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken()); // 세로
		n = Integer.parseInt(st.nextToken()); // 가로

		map = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isVisited = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				isVisited[i][j] = -1;
			}
		}
		System.out.println(dfs(0, 0));
	}

	static int dfs(int y, int x) {
		if (y == m - 1 && x == n - 1) {
			return 1;
		}

		if (isVisited[y][x] != -1) {
			return isVisited[y][x];
		}

		isVisited[y][x] = 0;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (!isOnBoard(ny, nx)) {
				continue;
			}
			if (map[y][x] > map[ny][nx]) {
				isVisited[y][x] += dfs(ny, nx);
			}
		}
		return isVisited[y][x];
	}

	static boolean isOnBoard(int y, int x) {
		return y >= 0 && y < m && x >= 0 && x < n;
	}
}