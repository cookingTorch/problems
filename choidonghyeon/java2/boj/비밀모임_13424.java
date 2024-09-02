import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 비밀모임_13424 {
	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			sb.append(solve(br)).append("\n");
		}
		System.out.print(sb);
	}

	private static int solve(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] dist = new int[N + 1][N + 1];

		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[a][b] = c;
			dist[b][a] = c;
		}

		floydWarshall(dist, N);

		int num = 0;
		int shortest = INF;

		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int[] friends = new int[K];
		for (int i = 0; i <K; i++) {
			friends[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 0; j < K; j++) {
				sum += dist[i][friends[j]];
			}
			if (sum < shortest) {
				shortest = sum;
				num = i;
			}
		}
		return num;
	}

	private static void floydWarshall(int[][] dist, int n) {
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				for (int k = 1; k < n + 1; k++) {
					dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
				}
			}
		}
	}
}
