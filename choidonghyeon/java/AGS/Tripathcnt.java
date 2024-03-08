import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[][] triangle = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < i + 1; j++) {
					triangle[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][][] dp = new int[n + 1][n + 1][2]; //dp[i][j][0]: i,j의 좌표에 도달할 수 있는 최대 경로 길이 dp[i][j][1]: i,j의 좌표에 도달할 수 있는 최대 경로의 수
			dp[1][1] = new int[]{triangle[0][0],1};

			for (int i = 1; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dp[i][j][0] == dp[i][j + 1][0]) {
						dp[i + 1][j + 1][0] = dp[i][j][0] + triangle[i][j];
						dp[i + 1][j + 1][1] = dp[i][j][1] + dp[i][j + 1][1]; //경로가
						continue;
					}
					if (dp[i][j][0] > dp[i][j + 1][0]) {
						dp[i + 1][j + 1][0] = dp[i][j][0] + triangle[i][j];
						dp[i + 1][j + 1][1] = dp[i][j][1];
						continue;
					}
					if (dp[i][j][0] < dp[i][j + 1][0]) {
						dp[i + 1][j + 1][0] = dp[i][j + 1][0] + triangle[i][j];
						dp[i + 1][j + 1][1] = dp[i][j + 1][1];
					}
				}
			}

			int maxDistance = 0;
			int maxDistanceFrequency = 0;

			for (int i = 1; i < n + 1; i++) {
				if (dp[n][i][0] > maxDistance) {
					maxDistance = dp[n][i][0];
					maxDistanceFrequency = dp[n][i][1];
				} else if (dp[n][i][0] == maxDistance) {
					maxDistanceFrequency += dp[n][i][1];
				}
			}

			System.out.println(maxDistanceFrequency);
		}
	}
}
