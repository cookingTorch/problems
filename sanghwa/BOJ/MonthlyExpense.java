package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MonthlyExpense {
    static int days;
    static int cnt;
    static int[] amounts;
    static int maxValue;
    static int tempCnt;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());

        days = Integer.parseInt(token.nextToken());
        cnt = Integer.parseInt(token.nextToken());
        amounts = new int[days];
        for (int i = 0; i < days; i++) {
            token = new StringTokenizer(buffer.readLine());
            int value = Integer.parseInt(token.nextToken());
            amounts[i] = value;
            maxValue = Math.max(maxValue, value);
            result += value;
        }

        binarySearch();
    }

    private static void binarySearch() {
        int startValue = maxValue;
        int endValue = result;

        while (startValue <= endValue) {
            int midValue = (startValue + endValue) / 2;
            tempCnt = 1;

            int change = midValue;
            for (int i = 0; i < days - 1; i++) {
                if (change - amounts[i] < amounts[i + 1]) {
                    tempCnt++;
                    change = midValue;
                } else
                    change = change - amounts[i];
                if (tempCnt > cnt)
                    break;
            }
            if (tempCnt <= cnt) {
                endValue = midValue - 1;
            } else {
                startValue = midValue + 1;
            }
        }
        System.out.println(startValue);
    }
}