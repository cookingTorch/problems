package jmb;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SynchronizingClocks {

    private static int MAX = 9999;
    private static int[][] switchs = {
          // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testcase = parseInt(bf.readLine());

        while (testcase-- > 0) {
            int[] clocks = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int result = solve(clocks, 0);
            if (result >= MAX) {
                result = -1;
            }
            System.out.println(result);
        }
    }

    private static int solve(int[] clocks, int switchNumber) {
        if (switchNumber == 10) {
            return Arrays.stream(clocks).allMatch(clock -> clock == 12) ? 0 : MAX;
        }

        int result = MAX;
        for (int count = 0; count < 4; count++) {
            result = Math.min(result, count + solve(clocks, switchNumber + 1));
            push(clocks, switchNumber);
        }
        return result;
    }

    private static void push(int[] clocks, int switchNumber) {
        for (int clock = 0; clock < switchs[switchNumber].length; clock++) {
            if (switchs[switchNumber][clock] == 1) {
                clocks[clock] += 3;
                if (clocks[clock] == 15) clocks[clock] = 3;
            }
        }
    }
}
