/*
* 문제명 - 극장 좌석 (BOJ 2302)
* 문제 유형 - 동적 프로그래밍
* 실행 속도 - 72 ms
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        boolean[] vips = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            vips[Integer.parseInt(br.readLine())] = true;
        }

        dp[0] = dp[1] = 1; //dp 초기화

        for (int i = 2; i < N + 1; i++) {
            dp[i] += dp[i - 1];
            if (vips[i] || vips[i - 1]) {
                continue;
            }
            dp[i] += dp[i - 2];
        }
        System.out.println(dp[N]);
    }
}
