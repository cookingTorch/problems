package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길 {

    static int[][] board;
    static int[][] dp;
    static int n, m;
    static int[][] dir = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        dp = new int[n][m];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        dp[n - 1][m - 1] = 1;
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int y, int x) {
        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;
        for (int[] d : dir) {
            int nextY = d[0] + y;
            int nextX = d[1] + x;

            if (isInArea(nextY, nextX) && board[y][x] > board[nextY][nextX]) {
                dp[y][x] += dfs(nextY, nextX);
            }
        }
        return dp[y][x];
    }

    private static boolean isInArea(int nextY, int nextX) {
        return nextY >= 0 && nextY < n && nextX >= 0 && nextX < m;
    }
}
