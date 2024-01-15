/**
 * https://algospot.com/judge/submission/recent/
 * 문제 이름 : BoggleGame
 * 문제 결과 : 성공
 * 소요 시간(정답까지) : -
 * 사용 알고리즘 : DP,dfs
 * 설명 : 풀이 힌트 참고
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class BoggleGame {
    static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            board = new char[5][5];
            for (int j = 0; j < 5; j++) {
                board[j] = br.readLine().toCharArray();
            }

            int words = Integer.parseInt(br.readLine());
            String[] wordsList = new String[words];
            HashMap<String, Boolean> answerDict = new HashMap<>();
            boolean[][][] dp;

            for (int k = 0; k < words; k++) {
                String word = br.readLine();
                dp = new boolean[5][5][word.length()];
                wordsList[k] = word;
                answerDict.put(word, false);

                for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 5; x++) {
                        if (board[y][x] == word.charAt(0)) {
                            dp[y][x][0] = true;
                            if (dfs(y, x, word, 0, dp)) {
                                answerDict.put(word, true);
                                break;
                            }
                        }
                    }
                    if (answerDict.get(word)) {
                        break;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String word : wordsList) {
                String result = answerDict.get(word) ? "YES" : "NO";
                sb.append(word);
                sb.append(" ");
                sb.append(result);
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }

    private static boolean dfs(int y, int x, String str, int idx, boolean[][][] dp) {
        if (idx == str.length() - 1) {
            return true;
        }
        for (int[] d : delta) {
            int my = y + d[0];
            int mx = x + d[1];
            if (0 <= my && my < 5 && 0 <= mx && mx < 5) {
                if (dp[my][mx][idx + 1]) {
                    continue;
                }
                if (board[my][mx] == str.charAt(idx + 1)) {
                    dp[my][mx][idx + 1] = true;
                    if (dfs(my, mx, str, idx + 1, dp)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
