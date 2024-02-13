import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new boolean[n][n];

            if (dfs(0,0)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean dfs(int i, int j) {
        if (i == board.length - 1 && j == board.length - 1) {
            return true;
        }
        int distance = board[i][j];

        if (distance + i < board.length && !dp[distance + i][j]) {
            if (dfs(distance + i, j)) {
                return true;
            }
            dp[distance + i][j] = true;
        }

        if (distance + j < board.length && !dp[i][distance + j]) {
            if (dfs(i, distance + j)) {
                return true;
            }
            dp[i][distance + j] = true;
        }
        return false;
    }
}
