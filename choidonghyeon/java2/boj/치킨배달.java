import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달 {
	static final int INF = 1000000000;

	static int N, M;
	static ArrayList<int[]> homes = new ArrayList<>();
	static ArrayList<int[]> chickens = new ArrayList<>();
	static int[] selected;
	static int[][] distances;
	static int answer = INF;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		selected = new int[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				String state = st.nextToken();
				if (state.equals("1")) {
					homes.add(new int[] {i, j});
				} else if (state.equals("2")) {
					chickens.add(new int[] {i, j});
				}
			}
		}

		distances = new int[homes.size()][chickens.size()];
		calculatedDistances(); //미리 모든집과 치킨집 사이의 거리를 계산하여 저장
		backtacking(0, 0);
		System.out.println(answer);
	}

	private static void calculatedDistances() {
		for (int i = 0; i < homes.size(); i++) {
			for (int j = 0; j < chickens.size(); j++) {
				distances[i][j] = Math.abs(homes.get(i)[0] - chickens.get(j)[0])
					+ Math.abs(homes.get(i)[1] - chickens.get(j)[1]);
			}
		}

	}

	private static void backtacking(int currIdx, int selectedIdx) {
		if (selectedIdx == M) {
			calculateChickenDistance();
			return;
		}

		if (chickens.size() - currIdx < M - selectedIdx) {
			return;
		}

		selected[selectedIdx] = currIdx;
		backtacking(currIdx + 1, selectedIdx + 1);
		backtacking(currIdx + 1, selectedIdx);
	}

	private static void calculateChickenDistance() {
		int totalChickenDistance = 0;
		for (int i = 0; i < homes.size(); i++) {
			int minimumChickenDistance = INF;
			for (int chickenIdx : selected) {
				minimumChickenDistance = Math.min(distances[i][chickenIdx], minimumChickenDistance);
			}
			totalChickenDistance += minimumChickenDistance;
		}
		answer = Math.min(answer, totalChickenDistance);
	}
}
