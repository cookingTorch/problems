import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨_배달 {
	private static ArrayList<Chicken> chickens = new ArrayList<>();
	private static ArrayList<House> houses = new ArrayList<>();
	private static int n, m, result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				String input = st.nextToken();
				if (input.equals("1")) {
					houses.add(new House(i, j));
				} else if (input.equals("2")) {
					chickens.add(new Chicken(i, j));
				}
			}
		}

		selectChickens(new boolean[chickens.size()], 0, 0);
		System.out.println(result);
	}

	private static void selectChickens(boolean[] selected, int start, int depth) {
		if (depth == m) {
			calculateChickenDistance(selected);
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			selected[i] = true;
			selectChickens(selected, i + 1, depth + 1);
			selected[i] = false;
		}
	}

	private static void calculateChickenDistance(boolean[] selected) {
		int totalDistance = 0;

		for (House house : houses) {
			int minDistance = Integer.MAX_VALUE;

			for (int i = 0; i < chickens.size(); i++) {
				if (selected[i]) {
					Chicken chicken = chickens.get(i);
					int distance = Math.abs(house.y - chicken.y) + Math.abs(house.x - chicken.x);
					minDistance = Math.min(minDistance, distance);
				}
			}

			totalDistance += minDistance;
		}

		result = Math.min(result, totalDistance);
	}

	public static class Chicken {
		int y;
		int x;

		public Chicken(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static class House {
		int y;
		int x;

		public House(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
