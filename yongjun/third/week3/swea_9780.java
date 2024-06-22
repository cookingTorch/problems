package third.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 *
 * @author yongjunhong
 * @since  2024. 6. 21.
 */
public class swea_9780 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int testCase = Integer.parseInt(st.nextToken());
		for (int number = 1; number < testCase + 1; number++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			long[] array = new long[1_000_000];
			st = new StringTokenizer(br.readLine());

			if (n == 1) {
				System.out.println("#" + number + " " + Integer.parseInt(st.nextToken()));
				continue;
			}

			array[0] = Integer.parseInt(st.nextToken());
			array[1] = Math.max(array[0], Integer.parseInt(st.nextToken()));

			for (int i = 2; i < n; i++) {
				array[i] = Math.max(Integer.parseInt(st.nextToken()) + array[i - 2], array[i - 1]);
			}
			System.out.println("#" + number + " " + array[n - 1]);
		}
	}
}