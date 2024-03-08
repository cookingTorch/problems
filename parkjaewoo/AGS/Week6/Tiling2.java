package AGS.Week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Tiling2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = parseInt(br.readLine());
        int[] dp = new int[101];

        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < 101; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        while (c-- > 0) {
            int n = parseInt(br.readLine());
            System.out.println(dp[n - 1]);
        }
    }
}
