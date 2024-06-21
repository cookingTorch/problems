import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 외계인침공 {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		int[] cities = new int[1000001];
		long[] dp = new long[1000001];

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st= new StringTokenizer(br.readLine());

			for (int i = 1; i <= N; i++) {
				cities[i] = Integer.parseInt((st.nextToken()));
			}

			dp[1] = cities[1];

			for (int i = 2; i <= N; i++) {
				dp[i] = Math.max(dp[i - 2] + cities[i], dp[i - 1]);
			}

			sb.append("#").append(test_case).append(" ").append(dp[N]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
