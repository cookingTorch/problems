import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 소문난_칠공주 {
	private static char[][] classMates = new char[5][5];
	private static boolean[][] visited = new boolean[5][5];
	private static int[][] dMove = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			classMates[i] = br.readLine().toCharArray();
		}
		checkStudents(0, 0);
		System.out.println(result);
	}

	private static void checkStudents(int depth, int start) {
		if (depth == 7) {
			if (calculateStudents(start - 1)) {
				result++;
			}
			return;
		}

		for (int i = start; i < 25; i++) {
			visited[i / 5][i % 5] = true;
			checkStudents(depth + 1, i + 1);
			visited[i / 5][i % 5] = false;
		}
	}

	private static boolean calculateStudents(int start) {
		boolean[][] cVisited = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			cVisited[i] = visited[i].clone();
		}

		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(start % 5, start / 5));

		int cnt = 0, dasom = 0;
		while (!queue.isEmpty()) {
			Point point = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = dMove[i][0] + point.y;
				int nx = dMove[i][1] + point.x;

				if (!isValid(ny, nx)) {
					continue;
				}

				if (cVisited[ny][nx]) {
					cVisited[ny][nx] = false;
					queue.add(new Point(nx, ny));
					cnt++;

					if (classMates[ny][nx] == 'S') {
						dasom++;
					}
				}
			}
		}
		return cnt == 7 && dasom >= 4;
	}

	private static boolean isValid(int y, int x) {
		return y >= 0 && y < 5 && x >= 0 && x < 5;
	}
}
