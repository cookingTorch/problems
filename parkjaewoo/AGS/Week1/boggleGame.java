package AGS.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 재귀함수 구현 -> 실패
public class boggleGame {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean isRight;
    public static boolean isInner(int x, int y) {
        return x >= 0 && y >= 0 && x < 5 && y < 5;
    }
    public static void findWord(char[][] board, char[] word, int _x, int _y, int idx) {
        if (idx == word.length - 1) {
            isRight = true;
            return;
        }

        for (int i = 0; i < 8; i++) {
            int x = dx[i] + _x;
            int y = dy[i] + _y;

            if (isInner(x,y) && board[x][y] == word[idx + 1]) {
                findWord(board, word, x, y, idx + 1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C, N;
        C = Integer.parseInt(st.nextToken());

        while (C-- > 0) {
            char[][] board = new char[5][5];

            for (int i = 0; i < 5; i++) {
                board[i] = br.readLine().toCharArray();
            }
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                isRight = false;
                char[] word = br.readLine().toCharArray();
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        if (board[j][k] == word[0]) {
                            findWord(board, word, j, k, 0);
                        }
                    }
                }
                if (isRight) {
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