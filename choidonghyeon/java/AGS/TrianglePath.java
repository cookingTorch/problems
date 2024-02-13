import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
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

            int[][] dp = new int[n + 1][n + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i + 1][j + 1] = Math.max(dp[i][j], dp[i][j + 1]) + triangle[i][j];
                }
            }
            System.out.println(Arrays.stream(dp[n]).max().getAsInt());
        }
    }
}
