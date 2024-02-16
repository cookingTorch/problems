package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pi {
    private static int[] dp;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while (testcase-- > 0) {
            numbers = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
            dp = new int[numbers.length + 1];
            Arrays.fill(dp, -1);
            int result = recursive(0);
            System.out.println(result);
        }
    }


    // 재귀 함수 3~5자리의 조합, 최소 합계
    // dp로 중복 탐색 제거
    private static int recursive(int n) {
        if (dp[n] != -1) return dp[n]; // 중복 탐색 최소화
        if (n == numbers.length) return 0;

        int result = 100000;
        for (int i = 3; i <= 5; i++) {
            if (n + i <= numbers.length) {
                result = Math.min(result, recursive(n + i) + getValue(n, i));
                dp[n] = result;
            }
        }
        return result;
    }

    // 난이도 구현
    /*
    1. 모든 숫자가 같을 때 (예: 333, 5555) 난이도: 1
    2. 숫자가 1씩 단조 증가하거나 단조 감소할 때 (예: 23456, 3210) 난이도: 2
    3. 두 개의 숫자가 번갈아 가며 출현할 때 (예: 323, 54545) 난이도: 4
    4. 숫자가 등차 수열을 이룰 때 (예: 147, 8642) 난이도: 5
    5. 그 외의 경우 난이도: 10
    * */
    private static int getValue(int n, int end) {

        // 연속된 항의 차 합계
        boolean isSame = true;
        for (int i = n; i < n + end - 1; i++) {
            if (numbers[i + 1] != numbers[i]) {
                isSame = false;
                break;
            }
        }

        // 난이도 1
        if (isSame) {
            return 1;
        }

        // 난이도 2
        boolean sequence = true;
        int prev = numbers[n + 1] - numbers[n];
        for (int i = n; i < n + end - 1; i++) {
            if (prev != numbers[i + 1] - numbers[i]) {
                sequence = false;
                break;
            }
        }

        if (sequence && Math.abs(numbers[n + 1] - numbers[n]) == 1) {
            return 2;
        }

        /// 난이도 4
        boolean alternating = true;
        for (int i = n; i < n + end - 2; i++) {
            if (numbers[i] != numbers[i + 2]) {
                alternating = false;
                break;
            }
        }

        if (alternating) {
            return 4;
        }

        // 난이도 5
        if (sequence) {
            return 5;
        }

        return 10;
    }
}
