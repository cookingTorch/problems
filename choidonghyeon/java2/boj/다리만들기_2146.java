import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기_2146 {
	static int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int N;
	static Queue<int[]> q = new LinkedList<>();
	static int[][] board;
	static int[][] dists;

	public static void main(String[] args) throws IOException {
		//1.입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		dists = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//2.그룹핑
		int groupId = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					board[i][j] = groupId;
					makeGroup(i, j, groupId);
					groupId++;
				}
			}
		}

		//3. 다리건설
		System.out.println(findBridge());
	}

	private static int findBridge() { //bfs
		int answer = 10001;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int y = curr[0];
			int x = curr[1];
			int groupId = curr[2];
			int dist = dists[y][x];

			for (int[] d : delta) {
				int ny = d[0] + y;
				int nx = d[1] + x;
				if (0 <= ny && 0 <= nx && ny < N && nx < N) {
					if (board[ny][nx] == 0) {
						board[ny][nx] = groupId;
						dists[ny][nx] = dist + 1;
						q.offer(new int[] {ny, nx, groupId});
					} else if (board[ny][nx] != groupId) {
						answer = Math.min(dist + dists[ny][nx], answer);
					}
				}
			}
		}
		return answer;
	}

	private static void makeGroup(int y, int x, int groupId) { //dfs
		boolean isCandidate = false;
		for (int[] d : delta) {
			int ny = d[0] + y;
			int nx = d[1] + x;
			if (0 <= ny && 0 <= nx && ny < N && nx < N) {
				if (board[ny][nx] == 1) {
					board[ny][nx] = groupId;
					makeGroup(ny, nx, groupId);
				} else if (board[ny][nx] == 0) {
					isCandidate = true;
				}
			}
		}
		if (isCandidate) {
			q.offer(new int[] {y, x, groupId});
		}
	}
}
