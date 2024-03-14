package first.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tripathcnt {
    static int[][] triangle;
    static int[][] cache;
    static int[][] countCache;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());
        while (testCase-- > 0) {
            n = Integer.parseInt(br.readLine());
            triangle = new int[n][n];
            cache = new int[n][n];
            countCache = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    if (!st.hasMoreElements()) {
                        break;
                    }
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int result = dp(0, 0);
            System.out.println(result);
        }
    }

    static int path(int x, int y) {
        if (y == n - 1) {
            return triangle[y][x];
        }
        if (cache[y][x] != 0) {
            return cache[y][x];
        }
        return cache[y][x] = triangle[y][x] + Math.max(path(x, y + 1), path(x + 1, y + 1));
    }

    static int dp(int x, int y) {
        if (y >= n - 1) {
            return 1;
        }
        if (countCache[y][x] != 0) {
            return countCache[y][x];
        }
        if (path(x, y + 1) >= path(x + 1, y + 1)) {
            countCache[y][x] += dp(x, y + 1);
        }
        if (path(x, y + 1) <= path(x + 1, y + 1)) {
            countCache[y][x] += dp(x + 1, y + 1);
        }
        return countCache[y][x];
    }
}