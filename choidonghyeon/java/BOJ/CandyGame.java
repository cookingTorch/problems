import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CandyGame {
    static char[][] board;
    static int n;
    static int answer = 1;

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

        for (int i = 0; i < n; i++) {
            findLongestInRow(i);
            findLongestInColumn(i);  //swap 하지 않았을때의 가장 긴 연속 부분 찾기

            for (int x = 0; x < n - 1; x++) {
                if (!isSameColor(board[i][x], board[i][x + 1])) { //인접한 두 원소의 색이 다르며, 같은 행일때
                    swap(i, x, i, x + 1);
                    calculateInSameRow(i, x, x + 1);
                    swap(i, x, i, x + 1);  //원상 복구
                }
            }

            for (int y = 0; y < n - 1; y++) {
                if (!isSameColor(board[y][i], board[y + 1][i])) { //인접한 두 원소의 색이 다르며, 같은 열일때
                    swap(y, i, y + 1, i);
                    calculateInSameColumn(i, y, y + 1);
                    swap(y, i, y + 1, i);
                }
            }
        }

        System.out.println(answer);
    }

    public static void calculateInSameRow(int y, int x1, int x2) { //인접한 두 원소가 같은 행인 경우
        findLongestInColumn(x1);
        findLongestInColumn(x2);
        findLongestInRow(y);
    }

    public static void calculateInSameColumn(int x, int y1, int y2) { //인접한 두 원소가 같은 열인 경우
        //열 탐색
        findLongestInColumn(x);
        findLongestInRow(y1);
        findLongestInRow(y2);
    }

    public static void findLongestInColumn(int x) { //특정 열에서 가장 긴 연속 부분 찾는 메서드
        int py = 1;
        for (int i = 0; i < n - 1; i++) {
            if (isSameColor(board[i][x], board[i + 1][x])) {
                py++;
            } else {
                answer = Math.max(answer, py);
                py = 1;
            }
        }
        answer = Math.max(answer, py); //반복문이 끝났을때도 다시 갱신.

    }

    public static void findLongestInRow(int y) { //특정 행에서 가장 긴 연속 부분 찾는 메서드
        int px = 1;
        for (int i = 0; i < n - 1; i++) {
            if (isSameColor(board[y][i], board[y][i + 1])) {
                px++;
            } else {
                answer = Math.max(answer, px);
                px = 1;
            }
        }
        answer = Math.max(answer, px);
    }

    public static boolean isSameColor(char e1, char e2) {
        return e1 == e2;
    }

    public static void swap(int y1, int x1, int y2, int x2) {
        char cmp;
        cmp = board[y1][x1];
        board[y1][x1] = board[y2][x2];
        board[y2][x2] = cmp;
    }
}
