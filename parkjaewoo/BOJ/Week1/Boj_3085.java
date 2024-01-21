package BOJ.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_3085 {

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int n;
    private static char[][] board;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        board = new char[n][n];

        for (int x = 0; x < board.length; x++) {
            board[x] = bf.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.max(searchDown(i), searchRight(i)));
        }

        if (max != n) {
            // 완전 탐색
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    isCheck(x,y);
                }
            }
        }

        System.out.println(max);
        bf.close();
    }

    private static void isCheck(int _x, int _y) {
        for (int direction = 0; direction < 4; direction++) {
            int x = dx[direction] + _x;
            int y = dy[direction] + _y;

            if (isInner(x, y)) {
                swap(x, y, _x, _y);
                if (direction < 2) {
                    max = Math.max(Math.max(searchDown(y),searchDown(_y)),
                            Math.max(searchRight(x), max));
                } else {
                    max = Math.max(Math.max(searchDown(y),searchRight(x)),
                            Math.max(searchRight(_x), max));
                }
                swap(x, y, _x, _y);
            }
        }
    }

    private static int searchDown(int y) {
        int[] tmp = new int[n];
        tmp[0] = 1;
        char c = board[0][y];

        for (int i = 1; i < n; i++) {
            if (c == board[i][y]) {
                tmp[i] = tmp[i - 1] + 1;
            } else {
                tmp[i] = 1;
                c = board[i][y];
            }
        }
        return Arrays.stream(tmp).max().getAsInt();
    }
    private static int searchRight(int x) {
        int[] tmp = new int[n];
        tmp[0] = 1;
        char c = board[x][0];

        for (int i = 1; i < n; i++) {
            if (c == board[x][i]) {
                tmp[i] = tmp[i - 1] + 1;
            } else {
                tmp[i] = 1;
                c = board[x][i];
            }
        }
        return Arrays.stream(tmp).max().getAsInt();
    }
    private static boolean isInner(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static void swap(int x, int y, int _x, int _y) {
        char tmp = board[x][y];
        board[x][y] = board[_x][_y];
        board[_x][_y] = tmp;
    }

}