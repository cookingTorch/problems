package jmb;

public class Wildcard {
    private String W;
    private String S;
    private int[][] dp;

    public Wildcard(String W, String S) {
        this.W = W;
        this.S = S;
        this.dp = new int[W.length() + 1][S.length() + 1];
        init_dp(W.length() + 1, S.length() + 1);
    }

    private void init_dp(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = -1;
            }
        }
    }

    public int isWildCard(int w, int s) {
        int result = dp[w][s];
        if (result != -1) {
            return result;
        }

        if (w < W.length() && s < S.length()
                && (W.charAt(w) == S.charAt(s) || W.charAt(w) == '?')) {
            return dp[w][s] = isWildCard(w + 1, s + 1);
        }

        if (w == W.length()) {
            return (s == S.length()) ? 1 : 0;
        }

        if (W.charAt(w) == '*') {
            if (isWildCard(w + 1, s) == 1
                    || (s < S.length() && isWildCard(w, s + 1) == 1)) {
                return dp[w][s] = 1;
            }
        }

        return 0;
    }
}
