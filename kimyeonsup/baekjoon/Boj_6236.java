package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_6236 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(token.nextToken());
        final int m = Integer.parseInt(token.nextToken());
        int[] days = new int[n];

        int min = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            days[i] = Integer.parseInt(br.readLine());
            min = Math.max(min, days[i]);
            max += days[i];
        }

        int result = binarySearch(min, max, days, m, 0);

        System.out.println(result);
    }

    private static int binarySearch(int start, int end, int[] days, int m, int result) {
        int mid = (start + end) / 2;

        if (start > end) {
            return result;
        }

        int amount = 0;
        int count = 0;
        for (int i = 0; i < days.length; i++) {
            if (amount < days[i]) {
                int withCnt = days[i] / mid;
                if (days[i] % mid > 0) withCnt++;
                amount = mid * withCnt;
                count += withCnt;
            }
            amount -= days[i];
        }

        if (count > m) {
            return binarySearch(mid + 1, end, days, m, mid);
        } else {
            return binarySearch(start, mid - 1, days, m, mid);
        }
    }
}
