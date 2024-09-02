import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15483 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int str1L = str1.length();
        int str2L = str2.length();

        //공백을 포함하여 검사를 하여 한칸 더 크게 만든다.
        int [][] dp = new int[str1L+1][str2L+1];

        for (int i = 0; i <= str1L; ++i)
            dp[i][0] = i;
        for (int i = 0; i <= str2L; ++i)
            dp[0][i] = i;

        for (int i = 1; i <= str1L; ++i) {
            for (int j = 1; j <= str2L; ++j) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    //dp[i-1][j] + 1: s1의 i-1번째 문자를 삭제한 경우
                    //dp[i][j-1] + 1: s2의 j-1번째 문자를 삽입한 경우
                    //dp[i-1][j-1] + 1: s1의 i-1번째 문자를 s2의 j-1번째 문자로 대체한 경우
                }
            }
        }
        System.out.println(dp[str1L][str2L]);
    }
}
