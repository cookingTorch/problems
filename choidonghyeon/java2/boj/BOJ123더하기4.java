import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ123더하기4 {
	/**
	 * dp[1] = 1   1
	 * dp[2] = 2   11 2
	 * dp[3] = 3   111 12 3
	 * dp[4] = 4   1111 112 22 13
	 * dp[5] = 5   11111 1112 122 113 23
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] dp = new int[100001];
		Arrays.fill(dp, 1);  //1로만 수를 이루는 경우는 항상 한개 존재

		int N = Integer.parseInt(br.readLine());
		for (int i = 2; i < 10001; i++) {
			dp[i] += dp[i - 2];
		}  // 2를 추가하여 수를 표현하는 방법 갱신

		for (int i = 3; i < 10001; i++) {
			dp[i] += dp[i - 3];
		} // 3을 추가하여 수를 표현 할 수 있는 방법 갱신

		while (N-- > 0) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}
	}
}
