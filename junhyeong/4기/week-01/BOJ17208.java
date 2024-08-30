import java.util.*;
import java.io.*;

public class BOJ17208 {
    static int n;
    static int burger;
    static int potato;
    static int[][][] dp;
    static int[] b_arr;
    static int[] p_arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        burger = Integer.parseInt(st.nextToken());
        potato = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][burger + 1][potato + 1];
        b_arr = new int[n + 1];
        p_arr = new int[n + 1];

        // -1로 채우기
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= burger; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // 주문
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            b_arr[i] = Integer.parseInt(st.nextToken());
            p_arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = solve(burger, potato, 0);
        System.out.println(ans);
    }

    private static int solve(int b, int p, int order) {
        if (order >= n)
            return 0;

        if (dp[order][b][p] != -1)
            return dp[order][b][p];

        int choose = 0;
        int no_choose = 0;
        if (b_arr[order + 1] <= b && p_arr[order + 1] <= p) {
            choose = solve(b - b_arr[order + 1],p - p_arr[order + 1], order + 1) + 1;
        }
        no_choose = solve(b, p, order + 1);
        dp[order][b][p] = Math.max(choose, no_choose);

        return dp[order][b][p];
    }
}
