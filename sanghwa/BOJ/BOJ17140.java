package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17140 {

	static class Number implements Comparable<Number> {
		int n;
		int cnt;

		Number(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Number o) {
			if (this.cnt != o.cnt) {
				return Integer.compare(this.cnt, o.cnt);
			}
			return Integer.compare(this.n, o.n);
		}
	}

	static int[][] board = new int[100][100];
	static int r, c, k;
	static int rowLen, colLen;

	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(buffer.readLine());

		r = Integer.parseInt(token.nextToken()) - 1;
		c = Integer.parseInt(token.nextToken()) - 1;
		k = Integer.parseInt(token.nextToken());

		for (int i = 0; i < 3; i++) {
			token = new StringTokenizer(buffer.readLine());
			for (int j = 0; j < 3; j++) {
				board[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		rowLen = 3;
		colLen = 3;

		int time = 0;
		while (board[r][c] != k && time <= 100) {
			if (rowLen >= colLen) {
				doR();
			} else {
				doC();
			}
			time++;
		}

		System.out.println(time <= 100 ? time : -1);
	}

	static void doR() {
		int maxCol = 0;
		for (int i = 0; i < rowLen; i++) {
			maxCol = Math.max(maxCol, processLine(board[i], i, true));
		}
		colLen = Math.min(maxCol, 100);
	}

	static void doC() {
		int maxRow = 0;
		for (int i = 0; i < colLen; i++) {
			int[] column = new int[100];
			for (int j = 0; j < rowLen; j++) {
				column[j] = board[j][i];
			}
			maxRow = Math.max(maxRow, processLine(column, i, false));
		}
		rowLen = Math.min(maxRow, 100);
	}

	static int processLine(int[] line, int index, boolean isRow) {
		int[] count = new int[101];
		List<Number> numbers = new ArrayList<>();

		for (int num : line) {
			if (num != 0) count[num]++;
		}

		for (int i = 1; i <= 100; i++) {
			if (count[i] > 0) {
				numbers.add(new Number(i, count[i]));
			}
		}

		Collections.sort(numbers);

		int size = 0;
		for (Number num : numbers) {
			if (size >= 100) break;
			if (isRow) {
				board[index][size++] = num.n;
				board[index][size++] = num.cnt;
			} else {
				board[size++][index] = num.n;
				board[size++][index] = num.cnt;
			}
		}

		for (int i = size; i < 100; i++) {
			if (isRow) board[index][i] = 0;
			else board[i][index] = 0;
		}

		return size;
	}
}