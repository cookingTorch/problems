package jmb;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Fence {

    /**
     boardCount 판자 수
     heights 판자들의 높이 배열
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testcase = parseInt(bf.readLine());

        while (testcase-- > 0) {
            // init
            final int boardCount = parseInt(bf.readLine());
            int[] heights = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // N * M , n = boardCount, m = 연속된 숫자 길이
            // 선형 탐색으로 판자 n을 탐색한다.
            // 탐색 시 양쪽 방향을 탐색하고, 연속되지 않으면 종료
            // 연속된다. = (n <= n + 1)
            int max = getWidthsMax(boardCount, heights);

            System.out.println(max);
        }
    }

    // 판자들의 너비 중 가장 큰 값을 구한다.
    private static int getWidthsMax(int boardCount, int[] heights) {
        // 판자 너비를 담을 Hash Map
        int max = 0;
        // 판자를 탐색
        for (int index = 0; index < heights.length; index++) {

            // 연속된 숫자를 구하는 메서드 호출
            int continuousNumber = getContinuousNumber(heights, index);

            // 판자 너비 계산
            int width = heights[index] * continuousNumber;
            max = Math.max(max, width);
        }

        return max;
    }

    private static int getContinuousNumber(int[] heights, int start) {
        // 본인 카운트를 위한 1
        int count = 0;
        int height = heights[start];
        for (int index = start; index < heights.length; index++) {
            int target = heights[index];
            if (height > target) {
                break;
            }
            count++;
        }

        for (int index = start - 1; index >= 0; index--) {
            int target = heights[index];
            if (height > target) {
                break;
            }
            count++;
        }
        return count;
    }
}
