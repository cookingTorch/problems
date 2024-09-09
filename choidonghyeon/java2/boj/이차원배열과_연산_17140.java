import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 이차원배열과_연산_17140 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());

		int[][] board = new int[100][100];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int sec = 0;

		int[] showCnt = new int[101];
		List<Integer> stack = new ArrayList<>();
		int maxRow = 3;
		int maxCol = 3;
		while (sec++ <= 100) {
			if (board[r][c] == k) {
				System.out.println(sec - 1);
				return;
			}

			if (maxRow >= maxCol) {
				int nextMaxCol = 0;
				for (int row = 0; row < maxRow; row++) {
					for (int col = 0; col < maxCol; col++) {
						if (board[row][col] == 0) {
							continue;
						}
						if (showCnt[board[row][col]]++ == 0) {
							stack.add(board[row][col]);
						}
					}

					stack.sort((x,y) -> {
						if (showCnt[x] != showCnt[y]) {
							return showCnt[y] - showCnt[x];
						}
						return y - x;
					});

					int currCol = 0;

					while (!stack.isEmpty()) {
						Integer num = stack.remove(stack.size() - 1);
						if (currCol < 100) {
							board[row][currCol] = num;
							currCol = currCol + 1;

							if (currCol < 100) {
								board[row][currCol] = showCnt[num];
								currCol = currCol + 1;
							}
						}
						showCnt[num] = 0;
					}
					nextMaxCol = Math.max(nextMaxCol, currCol);

					for (int i = currCol; i < maxCol; i++) {
						board[row][i] = 0;
					}
				}
				maxCol = nextMaxCol;
			} else {
				int nextMaxRow = 0;
				for (int col = 0; col < maxCol; col++) {
					for (int row = 0; row < maxRow; row++) {
						if (board[row][col] == 0) {
							continue;
						}
						if (showCnt[board[row][col]]++ == 0) {
							stack.add(board[row][col]);
						}
					}

					int currRow = 0;

					stack.sort((x,y) -> {
						if (showCnt[x] != showCnt[y]) {
							return showCnt[y] - showCnt[x];
						}
						return y - x;
					});

					while (!stack.isEmpty()) {
						Integer num = stack.remove(stack.size() - 1);
						if (currRow < 100) {
							board[currRow][col] = num;
							currRow = currRow + 1;

							if (currRow < 100) {
								board[currRow][col] = showCnt[num];
								currRow = currRow + 1;
							}
						}
						showCnt[num] = 0;
					}
					nextMaxRow = Math.max(nextMaxRow, currRow);

					for (int i = currRow; i < maxRow; i++) {
						board[i][col] = 0;
					}
				}
				maxRow = nextMaxRow;
			}
		}
		System.out.println(-1);
	}
}
