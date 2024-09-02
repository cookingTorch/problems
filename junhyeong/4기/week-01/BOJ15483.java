import java.io.*;

class BOJ15483 {
    static String a;
    static String b;
    static int[][] dp;

    static void solve() {
        dp = new int[1010][1010];

        for (int i = 0; i <= b.length(); i++) {
            for (int j = 0; j <= a.length(); j++) {
                if (i == 0)
                    dp[i][j] = j == 0 ? 0 : dp[i][j - 1] + 1;
                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] + 1;
                else {
                    dp[i][j] = myMin(dp[i][j - 1] + 1,
                            dp[i - 1][j] + 1,
                            dp[i - 1][j - 1] + comp(a.charAt(j - 1), b.charAt(i - 1)));
                }
            }
        }

        System.out.println(dp[b.length()][a.length()]);
    }

    private static int comp(char a, char b) {
        if (a == b)
            return 0;
        return 1;
    }

    private static int myMin(int a, int b, int c) {
        if (a > b) {
            return Math.min(b, c);
        }
        else {
            return Math.min(a, c);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();

        solve();
    }
}
