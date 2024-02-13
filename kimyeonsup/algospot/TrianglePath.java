package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrianglePath {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] triangle = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int j = 0;
                while (st.hasMoreElements()) {
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                    j++;
                }
            }

            System.out.println(getMaxTrianglePath(triangle, n));
        }


    }

    /*
    6
    1  2
    3  7  4
    9  4  1  7
    2  7  5  9  4

    6
    7  8
    10 15 12
    19 19 16 19
    21 26 24 28 23

    1. dp를 활용한 문제
    2. dp에는 이동히게 될 방향(아래, 아래오른쪽) 합들 중 최댓값을 저장한다.
     - dp[i][j] = max(dp[i][j] + dp[i - 1][j], dp[i][j] + dp[i - 1][j - 1]);
    * */
    private static int getMaxTrianglePath(int[][] triangle, int n) {
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                int curNumber = triangle[i][j];
                if (j == 0) {
                    dp[i][j] = curNumber + dp[i - 1][j];
                } else if (j < i) {
                    dp[i][j] = Math.max(curNumber + dp[i - 1][j], curNumber + dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = curNumber + dp[i - 1][j - 1];
                }
            }
        }
        return Arrays.stream(dp[n - 1]).max().getAsInt();
    }
}
