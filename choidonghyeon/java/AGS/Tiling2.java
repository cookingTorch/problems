import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static final int MOD = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine());
		int[] cases = new int[c];

		int maxN = 0;
		for (int i = 0; i < c; i++) {
			int n = Integer.parseInt(br.readLine());
			cases[i] = n;
			maxN = Math.max(n, maxN);
		}

		int[] dp = new int[maxN + 1]; //모든 케이스에서 상황은 변하지 않으므로 가장 큰 숫자를 기준으로 한번 dp 테이블 초기화 진행
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i < maxN + 1; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}

		for (int n : cases) {
			System.out.println(dp[n]);
		}
	}
}
