import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [BOJ] 최소 편집 / Gold 3 / 50m
 * - 92 ms
 * - DP
 * - dp[i][j]
 * - = B의 i 번째, A의 j 번째 문자까지의 prefix로 계산된 최소 편집 횟수
 * - dp[0][i] = i, dp[i][0] = i
 * - B[i] == A[j]이면
 * -   dp[i][j] = dp[i - 1][j - 1]
 * - B[i] != A[j]이면
 * -   dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
 * - dp[i]가 dp[i - 1]까지만 참조하므로 배열 두 개로 재사용
 * */
public class BOJ15483_최소편집 {
    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int len1;
        int len2;
        int[] prev;
        int[] curr;
        int[] temp;
        char ch;
        char[] str1;
        char[] str2;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        len1 = (str1 = br.readLine().toCharArray()).length; // A 길이
        len2 = (str2 = br.readLine().toCharArray()).length; // B 길이
        prev = new int[len1 + 1]; // dp[i - 1]
        curr = new int[len1 + 1]; // dp[i]
        for (i = 0; i <= len1; i++) {
            prev[i] = i; // dp[0][i] = i
        }
        for (i = 0; i < len2; i++) {
            ch = str2[i]; // B[i]
            curr[0] = prev[0] + 1; // dp[i][0] = i
            for (j = 1; j <= len1; j++) {
                if (ch == str1[j - 1]) { // B[i] == A[j]
                    curr[j] = prev[j - 1]; // dp[i][j] = dp[i-1][j-1]
                } else { // B[i] != A[j]
                    curr[j] = Math.min(prev[j - 1], Math.min(prev[j], curr[j - 1])) + 1;
                } // dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j - 1]) + 1
            }
            temp = prev; // dp[i]를 다음 dp[i-1]로 사용, 다음 dp[i]는 dp[i-1] 배열 재사용
            prev = curr;
            curr = temp;
        }
        System.out.print(prev[len1]); // dp[len2][len1]
    }
}
