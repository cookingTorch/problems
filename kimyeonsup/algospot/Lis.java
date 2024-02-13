package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lis {
    // 최대 부분 증가 수열 - LIS 구하기 (dp)
    /*
    3
    4
    1 2 3 4
    8
    5 4 3 2 1 6 7 8
    8
    5 6 7 8 1 2 3 4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] dp = new int[n];
            Arrays.fill(dp, 1);

            for (int i = 1; i < numbers.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (numbers[i] > numbers[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            System.out.println(Arrays.stream(dp).max().getAsInt());
        }
    }
}
