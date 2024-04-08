import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 과자나눠주기160401 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int answer = 0;
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());


		int[] snacks = new int[N];
		st = new StringTokenizer(br.readLine());

		int max = 0;
		for (int i = 0; i < N; ++i) {
			snacks[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, snacks[i]);
		}

		int left = 1;
		int right = max;
		int mid;
		while (left <= right) {
			mid = (left + right) / 2;
			if (isPossible(snacks, mid, M)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
	}

	private static boolean isPossible(int[] snacks, int mid, int target) {
		int cnt = 0;
		for(int snack : snacks) {
			cnt += snack / mid;
		}

		return cnt >= target;
	}
}
