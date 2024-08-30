package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CowBurgerTartTimer {
	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(buffer.readLine());
		int n = Integer.parseInt(token.nextToken());
		int m = Integer.parseInt(token.nextToken());
		int k = Integer.parseInt(token.nextToken());

		int[][] orders = new int[n][2];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(buffer.readLine());
			orders[i][0] = Integer.parseInt(token.nextToken());
			orders[i][1] = Integer.parseInt(token.nextToken());
		}

		System.out.println(solve(n, m, k, orders));
	}

	public static int solve(int n, int m, int k, int[][] orders) {
		int[][] dp = new int[m + 1][k + 1];
		int[][] nextDp = new int[m + 1][k + 1];

		for (int i = 0; i < n; i++) {
			int burger = orders[i][0];
			int fry = orders[i][1];

			for (int j = 0; j <= m; j++) {
				for (int l = 0; l <= k; l++) {
					nextDp[j][l] = dp[j][l];

					if (j >= burger && l >= fry) {
						nextDp[j][l] = Math.max(nextDp[j][l], dp[j - burger][l - fry] + 1);
					}
				}
			}

			int[][] temp = dp;
			dp = nextDp;
			nextDp = temp;
		}

		int maxOrders = 0;
		for (int j = 0; j <= m; j++) {
			for (int l = 0; l <= k; l++) {
				maxOrders = Math.max(maxOrders, dp[j][l]);
			}
		}

		return maxOrders;
	}
}