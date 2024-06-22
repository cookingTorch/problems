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
public class boj_15989 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int testCase = Integer.parseInt(st.nextToken());

		int[] cases = new int[10_001];
		cases[1] = 1;
		cases[2] = 2;
		cases[3] = 3;
		cases[4] = 4;
		for (int i = 5; i < 10_001; i++) {
			cases[i] = cases[i - 3] + i / 2 + 1;
		}

		StringBuilder sb = new StringBuilder();
		while (testCase-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			sb.append(cases[n]).append("\n");
		}
		System.out.println(sb);
	}
}