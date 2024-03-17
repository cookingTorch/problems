package first.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Snail {
    static double[][] cache;
    static double result;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(st.nextToken());
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 우물의 깊이
            m = Integer.parseInt(st.nextToken()); // 장마 기간의 길이

            cache = new double[n * 2 + 1][m + 1];
            /*
            원래는 정적으로 설정되는 0으로 메모리제이션을 했으나, 달팽이문제에서는 0을 활용하기때문에 초기값을 -1으로 설정함
             */
            for (int i = 0; i < n * 2 + 1; i++) {
                for (int j = 0; j < m + 1; j++) {
                    cache[i][j] = -1;
                }
            }
            result = 0;
            /*
            달팽이가 탈출하지 못 하는 경우의 수
            1. 매일 2m을 이동해도 나가지 못 하는 경우
            2. 비가 충분히 오지 않아 이동하지 못 하는 경우
             */
            if (n > (m * 2)) {
                sb.append(0).append("\n");
                continue;
            }
            if (n <= m) {
                sb.append(1).append("\n");
                continue;
            }
            sb.append(dp(0, 0)).append("\n");
        }
        System.out.println(sb);
    }

    static double dp(int height, int days) {
        /*
        장마기간이 다 지나기 전에 달팽이가 탈출하는 경우도 있으므로 길이 비교를 가장 먼저 배치했습니다.
         */
        if (height >= n) {
            return 1;
        }
        /*
        위 반복문에서 길이 비교를 했기 때문에 반환 값으로 0을 설정했습니다.
         */
        if (days >= m) {
            return 0;
        }
        if (cache[height][days] != -1) {
            return cache[height][days];
        }
        return cache[height][days] = 0.75 * dp(height + 2, days + 1) + 0.25 * dp(height + 1, days + 1);
    }
}