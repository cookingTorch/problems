package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tiling2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while (testcase-- > 0) {
            // 타일 가로 길이 n
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[n];

            // dp[n] = dp[n-1] + dp[n-2]
            // dp[0]과 dp[1] 초기화
            dp[0] = 1;
            if (n > 1) {
                dp[1] = 2;
                for (int i = 2; i < n; i++) {
                    dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
                }
            }

            System.out.println(dp[n - 1]);
        }
    }
}
