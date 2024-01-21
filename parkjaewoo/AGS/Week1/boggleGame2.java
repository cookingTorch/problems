package AGS.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 동적 계획법 -> 성공
public class boggleGame2 {
    static char[][] board;
    static int[][] dp;
    static int[][] tmp_dp;
    static char[] word;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static boolean isAnswer(int num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tmp_dp[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void copy_dp(int num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (dp[i][j] == num) {
                    tmp_dp[i][j] = num;
                }
                //System.out.print(tmp_dp[i][j] + " "); -> tmp_dp의 현황 볼 수 있음
            }
            //System.out.println();
        }
        //System.out.println("-----------");
    }
    public static boolean isInner(int x, int y) {
        return x >= 0 && y >= 0 && x < 5 && y < 5;
    }
    public static void findWord(int _x, int _y, int idx) {
        for (int i = 0; i < 8; i++) {
            int x = dx[i] + _x;
            int y = dy[i] + _y;

            if (isInner(x,y) && tmp_dp[x][y] == idx) {
                return;
            }
        }
        dp[_x][_y] = 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C, N;
        C = Integer.parseInt(st.nextToken());

        while (C-- > 0) {
            board = new char[5][5];

            for (int i = 0; i < 5; i++) {
                board[i] = br.readLine().toCharArray();
            }
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                tmp_dp = new int[5][5];
                word = br.readLine().toCharArray();

                for (int l = 0; l < word.length; l++) {
                    dp = new int[5][5];
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (board[j][k] == word[l]) {
                                dp[j][k] = l + 1;
                                findWord(j, k, l);
                            }
                        }
                    }
                    copy_dp(l + 1);
                }
                if (isAnswer(word.length)) {
                    System.out.println(String.valueOf(word) + " YES");
                } else {
                    System.out.println(String.valueOf(word) + " NO");
                }
            }

        }
    }
}

//1
//NNNNN
//NEEEN
//NEYEN
//NEEEN
//NNNNN
//1
//NEYN
//1
//URLPM
//XPRET
//GIAET
//XTNZY
//XOQRS
//6
//PRETTY
//GIRL
//REPEAT
//KARA
//PANDORA
//GIAZAPX