package AGS.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//
public class BoardCover2 {
    static int h, w;
    static char[][] board;
    static int count, answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(st.nextToken());

        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            board = new char[h][w];
            count = 0;
            answer = 0;

            // 보드판 그리기
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    board[i][j] = line.charAt(j);
                    if (board[i][j] == '.') {
                        count++;
                    }
                }
            }
            if (count % 3 != 0) {
                sb.append("0\n");
                continue;
            }
            for (int i = 0; i < h; i++) {
                backTracking(i, 0, 0);
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
    public static void backTracking(int i, int y, int idx) {
        /*for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------");*/

        for (int j = y; j < w; j++) {
            if (canDraw(i, j) && canDraw(i, j + 1) && canDraw(i + 1, j)) {
                firstCase(i, j, idx);
            }
            if (canDraw(i, j) && canDraw(i + 1, j) && canDraw(i + 1, j + 1)) {
                secondCase(i, j, idx);
            }
            if (canDraw(i, j) && canDraw(i, j + 1) && canDraw(i + 1, j + 1)) {
                thirdCase(i, j, idx);
            }
            if (canDraw(i, j) && canDraw(i + 1, j) && canDraw(i + 1, j - 1)) {
                fourthCase(i, j, idx);
            }
        }
        if (idx == count / 3) {
            answer++;
        }
    }
    public static void firstCase(int x, int y, int idx) {
        board[x][y] = '#';
        board[x][y + 1] = '#';
        board[x + 1][y] = '#';
        backTracking(x, y, idx + 1);
        board[x][y] = '.';
        board[x][y + 1] = '.';
        board[x + 1][y] = '.';
    }

    public static void secondCase(int x, int y, int idx) {
        board[x][y] = '#';
        board[x + 1][y] = '#';
        board[x + 1][y + 1] = '#';
        backTracking(x, y, idx + 1);
        board[x][y] = '.';
        board[x + 1][y] = '.';
        board[x + 1][y + 1] = '.';
    }
    public static void thirdCase(int x, int y, int idx) {
        board[x][y] = '#';
        board[x][y + 1] = '#';
        board[x + 1][y + 1] = '#';
        backTracking(x, y, idx + 1);
        board[x][y] = '.';
        board[x][y + 1] = '.';
        board[x + 1][y + 1] = '.';
    }
    public static void fourthCase(int x, int y, int idx) {
        board[x][y] = '#';
        board[x + 1][y] = '#';
        board[x + 1][y - 1] = '#';
        backTracking(x, y, idx + 1);
        board[x][y] = '.';
        board[x + 1][y] = '.';
        board[x + 1][y - 1] = '.';
    }
    public static boolean canDraw(int x, int y) {
        return x >= 0 && y >= 0 && x < h && y < w && board[x][y] != '#';
    }
}
