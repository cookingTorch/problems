package third.week1;

/**
 *
 * @author yongjunhong
 * @since 2024. 6. 7.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int y;
	int x;
	int count;
	int number;

	public Point(int y, int x, int count, int number) {
		this.y = y;
		this.x = x;
		this.count = count;
		this.number = number;
	}
}

public class boj_2146 {

	static int[][] map;
	static int[][] bridge;
	static int n, result = 1;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Point> coast = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		bridge = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[n][n];
		int number = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					map[i][j] = number;
					coast.add(new Point(i, j, 0, number));
					visited[i][j] = true;
					registIsland(i, j, number);
					number++;
				}
			}
		}

		bfs();
		System.out.println(result);
	}

	static void registIsland(int y, int x, int number) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {y, x});

		while (!queue.isEmpty()) {
			int[] point = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = point[0] + dy[i];
				int nx = point[1] + dx[i];

				if (!isOnBoard(ny, nx)) {
					continue;
				}

				if (visited[ny][nx]) {
					continue;
				}

				if (map[ny][nx] != 0) {
					visited[ny][nx] = true;
					bridge[ny][nx] = number;
					map[ny][nx] = number;
					coast.add(new Point(ny, nx, 0, number));
					queue.add(new int[] {ny, nx});
				}
			}
		}
	}

	static void bfs() {
		while (!coast.isEmpty()) {
			Point cur = coast.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (!isOnBoard(ny, nx))
					continue;

				if (bridge[ny][nx] == cur.number)
					continue;

				if (cur.number == map[ny][nx])
					continue;

				if (map[ny][nx] == 0) {
					bridge[ny][nx] = cur.number;
					coast.add(new Point(ny, nx, cur.count + 1, cur.number));
				} else {
					result = cur.count;
					return;
				}
			}
		}
	}

	static boolean isOnBoard(int y, int x) {
		return y >= 0 && x >= 0 && y < n && x < n;
	}
}