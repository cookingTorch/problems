package BOJ;
// https://www.acmicpc.net/problem/16401

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SharingSnacks {
    static int[] snacks;
    static int childNum;
    static int snackNum;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());

        childNum = Integer.parseInt(token.nextToken());
        snackNum = Integer.parseInt(token.nextToken());
        snacks = new int[snackNum];

        token = new StringTokenizer(buffer.readLine());
        for (int i = 0; i < snackNum; i++) {
            snacks[i] = Integer.parseInt(token.nextToken());
        }

        Arrays.sort(snacks);

        binarySearch(1, snacks[snackNum - 1]);
        System.out.println(result);
    }

    public static void binarySearch(int startValue, int endValue) {
        if (startValue > endValue) return;
        int midValue = (startValue + endValue) / 2;
        int cnt = 0;

        for (int i = 0; i < snackNum; i++) {
            cnt += snacks[i] / midValue;
        }
        if (cnt >= childNum) {
            if (result < midValue)
                result = midValue;
            binarySearch(midValue + 1, endValue);
        } else {
            binarySearch(startValue, midValue - 1);
        }
    }
}
