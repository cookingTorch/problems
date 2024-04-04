import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 용돈관리6236 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] amountDays = new int[N];
		int maxMoney = 0;
		for (int i = 0; i < N; i++) {
			amountDays[i] = Integer.parseInt(br.readLine());
			maxMoney = Math.max(maxMoney, amountDays[i]);
		}

		int left = 1;
		int right = 1000000000;
		int mid = 0;
		int answer = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (isPossible(mid, M, amountDays)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(answer);
	}

	private static boolean isPossible(int mid, int m, int[] amountDays) {
		int restMoney = mid;
		int withdrawCount = 1;

		for (int amountDay : amountDays) {
			if (mid < amountDay) {
				return false;
			}

			if (restMoney < amountDay) {
				withdrawCount++;
				restMoney = mid;
			}
			restMoney -= amountDay;
		}

		return withdrawCount <= m;
	}
}

