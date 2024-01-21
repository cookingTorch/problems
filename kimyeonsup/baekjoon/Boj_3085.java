package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_3085 {

    private static int[] directionX = {1, 0};
    private static int[] directionY = {0, 1};
    private static int n;
    private static char[][] board;
    private static char[][] tmpBoard;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        board = new char[n][n];

        for (int x = 0; x < board.length; x++) {
            board[x] = bf.readLine().toCharArray();
        }
        tmpBoard = Arrays.copyOf(board, n);

        // 변경하지 않았을 때 MAX
        for (int x = 0; x < n; x++) {
            max = Math.max(max, searchRight(x, 0));
        }

        for (int y = 0; y < n; y++) {
            max = Math.max(max, searchDown(0, y));
        }

        // 완전 탐색
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                isCheck(x, y);
            }
        }

        System.out.println(max);
        bf.close();
    }

    private static void isCheck(int _x, int _y) {
        for (int direction = 0; direction < directionX.length; direction++) {
            int x = directionX[direction] + _x;
            int y = directionY[direction] + _y;

            if (!isInner(x, y)) continue;

            if (board[x][y] != board[_x][_y]) {
                copyBoard();
                char tmpCh = tmpBoard[x][y];
                tmpBoard[x][y] = tmpBoard[_x][_y];
                tmpBoard[_x][_y] = tmpCh;

                if (directionX[direction] == 1) {
                    max = Math.max(max, Math.max(Math.max(searchDown(0, _y), searchRight(_x, 0)), searchRight(x, 0)));
                } else {
                    max = Math.max(max, Math.max(Math.max(searchDown(0, y), searchDown(0, _y)), searchRight(_x, 0)));
                }
            }
        }
    }

    private static void copyBoard() {
        for (int i = 0; i < board.length; i++) {
            tmpBoard[i] = Arrays.copyOf(board[i], n);
        }
    }

    private static int searchDown(int x, int y) {
        int[] tmp = new int[n];
        tmp[0] = 1;
        char c = tmpBoard[x][y];

        for (int i = 1; i < n; i++) {
            if (c == tmpBoard[i][y]) {
                tmp[i] = tmp[i - 1] + 1;
            } else {
                tmp[i] = 1;
                c = tmpBoard[i][y];
            }
        }

        return Arrays.stream(tmp).max().getAsInt();
    }

    private static int searchRight(int x, int y) {
        int[] tmp = new int[n];
        tmp[0] = 1;
        char c = tmpBoard[x][y];

        for (int i = 1; i < n; i++) {
            if (c == tmpBoard[x][i]) {
                tmp[i] = tmp[i - 1] + 1;
            } else {
                tmp[i] = 1;
                c = tmpBoard[x][i];
            }
        }
        return Arrays.stream(tmp).max().getAsInt();
    }

    private static boolean isInner(int x, int y) {
        return (x >= 0 && x < n) && (y >= 0 && y < n);
    }
}
