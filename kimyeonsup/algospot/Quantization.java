package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quantization {
    private final static int MAX = 987654321;
    private static int[][] dp;
    private static int[] partsSum;
    private static int[] numbers;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while (testcase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int partNumber = Integer.parseInt(st.nextToken());
            numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            partsSum = new int[n];
            dp = new int[101][11];

            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], -1);
            }

            Arrays.sort(numbers);
            // 구간 합 구하기
            partsSum[0] = numbers[0];
            for (int i = 1; i < n; i++) {
                partsSum[i] = partsSum[i - 1] + numbers[i];
            }

            System.out.println(quantize(0, partNumber));
        }
    }

    public static int quantize(int from, int partNumber) {
        // 주어진 수열 끝까지 탐색했을 경우 0
        if (from == n) return 0;

        // 더 탐색해야하는데 partNumber가 0인 경우
        if (partNumber == 0) return MAX;

        if (dp[from][partNumber] != -1) return dp[from][partNumber];

        int result = MAX;
        for (int i = 1; from + i <= n; i++) {
            result = Math.min(result, getMin(from, from + i - 1) + quantize(from + i, partNumber - 1));
        }

        return result;
    }

    public static int getMin(int start, int end) {
        int sum = partsSum[end] - (start == 0 ? 0 : partsSum[start - 1]);
        long selectNumber = Math.round(sum / ((end - start + 1) * 0.1) / 10);

        int result = 0;
        for (int i = start; i <= end; i++) {
            long diffSum = Math.abs(numbers[i] - selectNumber);
            result += (diffSum * diffSum);
        }

        System.out.println("result = " + result);
        return result;
    }
}
