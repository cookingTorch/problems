package BOJ;
// https://www.acmicpc.net/problem/15483

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumEdit {

	static char[] charsA;
	static char[] charsB;
	static int result = 0;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

		String strA = buffer.readLine();
		String strB = buffer.readLine();

		if (strA.equals(strB)) {
			System.out.println(result);
			return;
		}

		charsA = new char[strA.length()];
		charsB = new char[strB.length()];
		dp = new int[strB.length() + 1][strA.length() + 1];
		dp[0][0] = 0;
		for (int i = 1; i <= charsA.length; i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i <= charsB.length; i++) {
			dp[i][0] = i;
		}

		charsA = strA.toCharArray();
		charsB = strB.toCharArray();

		for (int i = 1; i <= charsB.length; i++) {
			for (int j = 1; j <= charsA.length; j++) {
				if (charsA[j - 1] == charsB[i - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
					continue;
				}
				dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j -1])) + 1;
			}
		}

		System.out.println(dp[charsB.length][charsA.length]);
	}
}
