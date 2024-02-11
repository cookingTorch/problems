package AGS.Week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class TrianglePath {
    private static int dynamicProgramming(int[][] dp, int n) {
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) { // 왼쪽 위 값과 바로 위 값을 자기 자신과 비교하며, 이 값을 메모이제이션
                dp[i][j] = Math.max(dp[i][j] + dp[i - 1][j - 1], dp[i][j] + dp[i - 1][j]);
            }
        }

        return Arrays.stream(dp[n]).max().getAsInt(); // dp[n] 배열 중 최대값 출력
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int c = parseInt(br.readLine());

        while (c-- > 0) {
            int n = parseInt(br.readLine());
            int[][] dp = new int[n + 1][n + 1];

            for (int i = 1; i < n + 1; i++) { // int[][] dp 2차원 배열에 값 입력
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < i + 1; j++) {
                    dp[i][j] = parseInt(st.nextToken());
                }
            }

            System.out.println(dynamicProgramming(dp, n));
        }
    }
}
