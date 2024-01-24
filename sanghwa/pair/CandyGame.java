package pair;

/*
 * 1Week pair
 * sang hwa, hui pyo
 * BOJ 3085
 */

import java.util.*;

public class CandyGame {
    static int mAnswer = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[][] candies = new String[n][n];

        Arrays.setAll(candies, i -> scanner.next().split(""));
        scanner.close();
        mAnswer = getLongestContinuousCount(candies, true);
        mAnswer = Math.max(mAnswer, getLongestContinuousCount(candies, false));
        swap(candies, true);
        swap(candies, false);
        System.out.println(mAnswer);
    }

    private static int getLongestContinuousCount(String[][] candies, boolean isCheckRow) {
        int result = 0;
        String pre_target = "X";

        for (int i = 0; i < candies.length; i++) {
            int max = 0;
            for (int j = 0; j < candies[i].length; j++) {
                String target = isCheckRow ? candies[i][j] : candies[j][i];
                if (pre_target.equals(target))
                    max++;
                else
                    max = 1;
                pre_target = target;
            }
            result = Math.max(result, max);
        }
        return result;
    }

    private static void swap(String[][] candies, boolean isCheckRow) {
        if (isCheckRow) {
            for (int i = 0; i < candies.length; i++) {
                for (int j = 0; j < candies[i].length - 1; j++) {
                    if (candies[i][j].equals(candies[i][j + 1])) continue;
                    else {
                        String temp = candies[i][j];
                        candies[i][j] = candies[i][j + 1];
                        candies[i][j + 1] = temp;
                        int rowResult = getLongestContinuousCount(candies, true);
                        int colResult = getLongestContinuousCount(candies, false);
                        mAnswer = Math.max(mAnswer, Math.max(rowResult, colResult));
                        temp = candies[i][j + 1];
                        candies[i][j + 1] = candies[i][j];
                        candies[i][j] = temp;
                    }
                }
            }
        } else {
            for (int i = 0; i < candies[0].length; i++) {
                for (int j = 0; j < candies.length - 1; j++) {
                    if (candies[j][i].equals(candies[j + 1][i])) continue;
                    else {
                        String temp = candies[j][i];
                        candies[j][i] = candies[j + 1][i];
                        candies[j + 1][i] = temp;
                        int rowResult = getLongestContinuousCount(candies, true);
                        int colResult = getLongestContinuousCount(candies, false);
                        mAnswer = Math.max(mAnswer, Math.max(rowResult, colResult));
                        temp = candies[j + 1][i];
                        candies[j + 1][i] = candies[j][i];
                        candies[j][i] = temp;
                    }
                }
            }
        }
    }
}

