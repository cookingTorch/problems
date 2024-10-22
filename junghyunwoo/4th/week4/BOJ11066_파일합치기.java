import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 파일 합치기 / Gold 3 / 30m
 * - 136 ms
 * - 크누스 최적화
 * - dp[i][j] : i ~ j - 1 까지 합치는데 필요한 최소비용
 * - sum[i][j] : i ~ j - 1 까지 소설 장 수 구간 합
 * - i <= mid <= j 일 때,
 * -   dp[i][j] = min(dp[i][mid] + dp[mid][j]) + sum[i][j]
 * - a <= b <= c <= d 일 때,
 * -   sum[a][c] + sum[b][d] <= sum[a][d] + sum[b][c] 이고,
 * -   sum[b][c] <= sum[a][d]
 * - sum[i][j]가 사각 부등식과 단조성을 만족하므로 크누스 최적화
 * - mids[i][j] : dp[i][j]를 최소로 만드는 mid
 * - mids[i][j - 1] <= mids[i][j] <= mids[i + 1][j]
 * */
public class BOJ11066_파일합치기 {
    private static final int INF = Integer.MAX_VALUE;
    private static final int MAX_K = 502;
    private static final char LINE_BREAK = '\n';

    private static int[] prefix;
    private static int[][] dp;
    private static int[][] mids;
    private static BufferedReader br;

    private static final int solution() throws IOException {
        int i;
        int j;
        int k;
        int len;
        int mid;
        int sum;
        int val;
        StringTokenizer st;

        k = Integer.parseInt(br.readLine()) + 1;
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 1; i < k; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken()); // 소설 장 수 누적 합
        }
        for (i = 1; i < k - 1; i++) { // 구간 길이 2 일 때
            mids[i][i + 2] = i + 1; // mid값 유일
            dp[i][i + 2] = prefix[i + 1] - prefix[i - 1]; // 비용 = 두 소설 합
        }
        for (len = 3; len < k; len++) { // 구간 길이 3 부터
            for (i = 1; i <= k - len; i++) { // 시작점
                j = i + len; // 시작점 + 구간 길이
                dp[i][j] = INF; // dp 값 초기화
                sum = prefix[j - 1] - prefix[i - 1]; // 구간 합
                for (mid = mids[i][j - 1]; mid <= mids[i + 1][j]; mid++) { // mid 범위 크누스 최적화
                    val = dp[i][mid] + dp[mid][j] + sum; // 현재 mid로 계산될 dp[i][j] 값
                    if (val < dp[i][j]) { // 최소값이면
                        dp[i][j] = val; // 최소값 갱신
                        mids[i][j] = mid; // mids 갱신
                    }
                }
            }
        }
        return dp[1][k]; // 전체 구간에 대한 dp 반환
    }

    public static void main(String[] args) throws IOException {
        int t;
        StringBuilder sb;

        prefix = new int[MAX_K];
        dp = new int[MAX_K][MAX_K];
        mids = new int[MAX_K][MAX_K];
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
