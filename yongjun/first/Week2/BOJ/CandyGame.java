package first.Week2.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CandyGame {
    static char[][] board;
    static int n;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = inputLine.charAt(j);
            }
        }
        /*
        초기 입력값의 최대 길이 값을 구하는 반복문입니다.
         */
        for (int i = 0; i < n; i++) {
            findColumn(i);
            findRow(i);
        }


        for (int i = 0; i < n; i++) {
            // 행에 위치한 사탕을 교환하는 반복문입니다.
            for (int x = 0; x < n - 1; x++) {
                if (compareColor(board[i][x], board[i][x + 1])) {
                    swap(i, x, i, x + 1);
                    findLongestX(i, x, x + 1);
                    swap(i, x, i, x + 1);
                }
            }

            // 열에 위치한 사탕을 교환하는 반복문입니다.
            for (int y = 0; y < n - 1; y++) {
                if (compareColor(board[y][i], board[y + 1][i])) {
                    swap(y, i, y + 1, i);
                    findLongestY(i, y, y + 1);
                    swap(y, i, y + 1, i);
                }
            }
        }
        System.out.println(result);
    }

    // 행에 위치한 사탕을 바꾸는 경우, 바꾼 x 값에 대한 열을 검사하고 바꾼 캔디가 위치한 행을 검사하면 됩니다. (3가지 경우)
    public static void findLongestX(int y, int x1, int x2) {
        //행 탐색
        findColumn(x1);
        findColumn(x2);
        findRow(y);
    }
    // 열에 위치한 사탕을 바꾸는 경우, 바꾼 y 값에 대한 행을 검사하고 바꾼 캔디가 위치한 열을 검사하면 됩니다. (3가지 경우)
    public static void findLongestY(int x, int y1, int y2) {
        //열 탐색
        findColumn(x);
        findRow(y1);
        findRow(y2);
    }

    public static void findColumn(int x) {
        int py = 1;
        for (int i = 0; i < n - 1; i++) {
            if (!compareColor(board[i][x], board[i + 1][x])) {
                py++;
            } else {
                result = Math.max(result, py);
                py = 1;
            }
        }
        result = Math.max(result, py);

    }

    public static void findRow(int y) {
        int px = 1;
        for (int i = 0; i < n - 1; i++) {
            if (!compareColor(board[y][i], board[y][i + 1])) {
                px++;
            } else {
                result = Math.max(result, px);
                px = 1;
            }
        }
        result = Math.max(result, px);
    }

    public static boolean compareColor(char e1, char e2) {
        return e1 != e2;
    }

    public static void swap(int y1, int x1, int y2, int x2) {
        char cmp;
        cmp = board[y1][x1];
        board[y1][x1] = board[y2][x2];
        board[y2][x2] = cmp;
    }
}