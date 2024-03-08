package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Snail {
    private static double[][] cache;
    private static int depth;
    private static int dayCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        // snail(day, height) = 0.75 * snail(day + 1, height + 2) + 0.25 * snail(day + 1, height + 1)
        /*
        4
        5 4
        5 3
        4 2
        3 2
        * */
        while (testcase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            depth = Integer.parseInt(st.nextToken());
            dayCount = Integer.parseInt(st.nextToken());
            cache = new double[dayCount + 2][depth + 2];
            for (int i = 0; i < dayCount + 2; i++) {
                Arrays.fill(cache[i], -1);
            }
            System.out.println(String.format("%.10f", snail(0, 0)));
        }
    }

    private static double snail(int day, int height) {
        double value = cache[day][height];
        if (value != -1) {
            return value;
        }

        // day가 끝나기 전에 목표에 도달했을 때
        if (height >= depth) {
            return 1;
        }
        // 기저 사례 장마가 끝나고 우물 끝가지 올라갔을때
        if (day == dayCount) {
            return height >= depth ? 1 : 0;
        }

        return cache[day][height] = 0.75 * snail(day + 1, height + 2) + 0.25 * snail(day + 1, height + 1);
    }
}
