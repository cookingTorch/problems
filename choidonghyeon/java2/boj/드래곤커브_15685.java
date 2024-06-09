import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 드래곤커브_15685 {
	static class Pos {
		int y;
		int x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public Pos rotate90(Pos axis) {
			int dy = -axis.y;
			int dx = -axis.x;

			return new Pos(x + dx - dy, -(y + dy) - dx);
		}

		@Override
		public String toString() {
			return "Pos{(" + x + "," + y + ")}";
		}
	}

	public static void main(String[] args) throws IOException {
		List<Pos>[][] dragonCurves = new List[4][11];

		dragonCurves[0][0] = List.of(new Pos(0, 0), new Pos(0, 1));
		dragonCurves[1][0] = List.of(new Pos(0, 0), new Pos(-1, 0));
		dragonCurves[2][0] = List.of(new Pos(0, 0), new Pos(0, -1));
		dragonCurves[3][0] = List.of(new Pos(0, 0), new Pos(1, 0));

		//1~11세대 4방향 드래곤 커브 리스트형태로 초기화
		for (int i = 0; i < 4; i++) { //방향
			for (int j = 1; j < 11; j++) { //세대
				dragonCurves[i][j] = makeNextGeneration(dragonCurves[i][j - 1]);
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] board = new boolean[101][101];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			List<Pos> dragonCurve = dragonCurves[d][g];
			for (Pos currPos : dragonCurve) {
				board[y + currPos.y][x + currPos.x] = true;
			}
		}

		int answer = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (board[i][j] && board[i][j + 1] && board[i + 1][j] && board[i + 1][j + 1]) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}

	private static List<Pos> makeNextGeneration(List<Pos> prevGeneration) {
		List<Pos> nextGen = new ArrayList<>(prevGeneration);
		Pos axis = prevGeneration.get(prevGeneration.size() - 1); //끝점 -> 이전세대의 마지막 인덱스 위치

		for (int i = prevGeneration.size() - 2; i > -1; i--) {//다음 세대의 끝점은 항상 (0,0)을 90도 회전 시킨것이여하므로 역방향 진행
			nextGen.add(prevGeneration.get(i).rotate90(axis));
		}
		return nextGen;
	}
}
