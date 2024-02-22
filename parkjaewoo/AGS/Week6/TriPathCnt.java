package AGS.Week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class TriPathCnt {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int c = parseInt(br.readLine());

        while (c-- > 0) {
            int n = parseInt(br.readLine());
            int[][] board = new int[n + 1][n + 1];
            int[][] pathBoard = new int[n + 1][n + 1];
            int max = 0;
            int cnt = 0;

            for (int i = 1; i < n + 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= i; j++) {
                    board[i][j] = parseInt(st.nextToken());
                }
            }

            pathBoard[0][0] = 1;
            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= i; j++) {
                    int cmp1 = board[i][j] + board[i - 1][j];
                    int cmp2 = board[i][j] + board[i - 1][j - 1];
                    if (cmp1 > cmp2) {
                        board[i][j] = cmp1;
                        pathBoard[i][j] += pathBoard[i - 1][j];
                    } else if (cmp1 < cmp2){
                        board[i][j] = cmp2;
                        pathBoard[i][j] += pathBoard[i - 1][j - 1];
                    } else {
                        board[i][j] = cmp1;
                        pathBoard[i][j] += pathBoard[i - 1][j] + pathBoard[i - 1][j - 1];
                    }
                }
            }
            for (int i = 1; i < n + 1; i++) {
                int cmp1 = board[n][i] + board[n - 1][i - 1];
                int cmp2 = board[n][i] + board[n - 1][i];

                if (max < cmp1) {
                    max = cmp1;
                    cnt = pathBoard[n - 1][i - 1];
                } else if (max == cmp1) {
                    cnt += pathBoard[n - 1][i - 1];
                }

                if (max < cmp2) {
                    max = cmp2;
                    cnt = pathBoard[n - 1][i];
                } else if (max == cmp2) {
                    cnt += pathBoard[n - 1][i];
                }
            }
            System.out.println(cnt);
        }
    }
}
