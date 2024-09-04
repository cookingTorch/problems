import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 카우버거 알바생 / Gold 4 / 13m
 * - 116 ms
 * - Knapsack
 * - 용량 (M, K) 가방에
 * - 무게 (x, y), 가치 1인 N 가지 짐들로 만들 수 있는 최대 가치
 * - Knapsack 뒤에서부터 탐색 : dp 1차원 줄이기
 * - dp[i][j] = 용량 (i, j)에 현재까지의 짐들로 만들 수 있는 최대 가치
 * - dp[i][j] = max(dp[i][j], dp[i - x][j - y] + 1)
 * */
public class BOJ17208_카우버거알바생 {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int k;
        int i;
        int j;
        int burgers;
        int fries;
        int[][] dp;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[m + 1][k + 1];
        while (--n > 0) { // N - 1 가지 짐
            st = new StringTokenizer(br.readLine()); // 현재 짐 정보
            burgers = Integer.parseInt(st.nextToken()); // x
            fries = Integer.parseInt(st.nextToken()); // y
            for (i = m; i >= burgers; i--) { // Knapsack 뒤에서부터 탐색 : dp 1차원 줄이기
                for (j = k; j >= fries; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - burgers][j - fries] + 1);
                } // dp[i][j] = max(dp[i][j], dp[i - x][j - y] + 1)
            }
        }
        st = new StringTokenizer(br.readLine()); // 마지막 짐
        burgers = Integer.parseInt(st.nextToken()); // x
        fries = Integer.parseInt(st.nextToken()); // y
        if (m >= burgers && k >= fries) { // dp[M][K]만 확인
            System.out.print(Math.max(dp[m][k], dp[m - burgers][k - fries] + 1));
        } else {
            System.out.print(dp[m][k]);
        }
    }
}
