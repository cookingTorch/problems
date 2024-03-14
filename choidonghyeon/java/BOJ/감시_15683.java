import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Cctv {
		private static final int[][] cctvDirections = { //cctv의 감시방향 directions 배열의 방향을 인덱스 값으로 취함.
			{0}, //1번 cctv 타입 오른쪽 방향 ->
			{0, 2},
			{0, 3},
			{0, 2, 3},
			{0, 1, 2, 3}
		};
		private static final int[] rotateCount = {4, 2, 4, 4, 1}; //cctv의 타입별 회전횟수 회전이 유의미한경우만.

		private final int y;
		private final int x;
		private final int type;

		public Cctv(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}

		public int getY() {
			return y;
		}

		public int getX() {
			return x;
		}

		public int getType() {
			return type;
		}

		public int getRotateCount() { //cctv의 타입별 회전횟수
			return rotateCount[this.getType() - 1];
		}

		public int[] getDirections() { //cctv의 타입별 감시 방향
			return cctvDirections[this.getType() - 1];
		}
	}

	static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //(0,1) 방향을 시작으로 90도 회전 방향을 차례대로 초기화
	static int answer;
	static int[][] board;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //세로
		M = Integer.parseInt(st.nextToken()); //가로

		board = new int[N][M];
		answer = 0;

		List<Cctv> cctvs = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 0) {
					answer++; //초기 빈 공간 갯수 카운트
				} else if (1 <= value && value <= 5) {
					cctvs.add(new Cctv(i, j, value));
				}
				board[i][j] = value;
			}
		}

		if (!cctvs.isEmpty()) {
			backtrack(0, cctvs);
		}

		System.out.println(answer);
	}

	private static void backtrack(int currIndex, List<Cctv> cctvs) {
		Cctv currCctv = cctvs.get(currIndex);
		//mark #
		for (int i = 0; i < currCctv.getRotateCount(); i++) { //현재 cctv type의 회전 횟수 만큼 반복
			markAllDirecitions(currIndex, currCctv, i);
			if (currIndex == cctvs.size() - 1) {
				countZero(); //마지막 cctv의 경우 현재 보드의 0를 카운트해서 answer값 갱신
			} else {
				backtrack(currIndex + 1, cctvs);
			}
			markAllDirecitions(currIndex, currCctv, i); //마크한
		}
	}

	private static void markAllDirecitions(int currIndex, Cctv currCctv, int i) {
		for (int direction : currCctv.getDirections()) {
			int[] rotatedDirection = directions[(direction + i) % 4];  // cctv의 방향이 (0,1) 방향이면 i번 만큼 90도 회전했을 경우의 방향.
			mark(currCctv,rotatedDirection, currIndex + 1);
		}
	}

	private static void mark(Cctv currCctv, int[] rotatedDirection, int currIndex) {
		int currY = currCctv.getY() + rotatedDirection[0];
		int currX = currCctv.getX() + rotatedDirection[1];

		while (0 <= currY && currY < N && 0 <= currX && currX < M ) {
			if (board[currY][currX] == 0 || board[currY][currX] == -currIndex) { //현재 cctv(currIndex)에의해 마킹한 구역만 되돌림.
				board[currY][currX] = board[currY][currX] * -1 - currIndex;  // ex) 현재 cctv 가 1번째 일때, 0의 경우 -> -1 로 마킹 혹은 -1 로 마킹된 경우 0으로 되돌림
			} else if (board[currY][currX] == 6) { //벽에 도달할 경우 stop
				break;
			}
			currY += rotatedDirection[0];
			currX += rotatedDirection[1];
		}
	}

	private static void countZero() {
		int temp = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0) {
					temp++;
				}
			}
		}
		answer = Math.min(temp, answer);
	}
}
