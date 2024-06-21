import java.io.*;
import java.util.*;

/*
참고 :
https://steady-coding.tistory.com/142
 */

public class BOJ1520 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(solve(arr, dp, n, m, 0, 0));
    }

    static short[] dx = {-1, 0, 1, 0};
    static short[] dy = {0, -1, 0, 1};
    private static int solve(int[][] arr, int[][] dp, int n, int m, int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (arr[nx][ny] >= arr[x][y]) continue;

            dp[x][y] += solve(arr, dp, n, m, nx, ny);
        }

        return dp[x][y];
    }

}
