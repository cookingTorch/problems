package AGS;

// https://algospot.com/judge/problem/read/BOARDCOVER#
// 풀이는 책 참고 (p.161)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoardCover {
        static int[][][] coverType = {
                { {0, 0}, {1, 0}, {0, 1} },
                { {0, 0}, {0, 1}, {1, 1} },
                { {0, 0}, {1, 0}, {1, 1} },
                { {0, 0}, {1, 0}, {1, -1} }
        };
    public static void main(String[] args) {
        int caseNum, height, weight;

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringToken = new StringTokenizer(buffer.readLine());

            caseNum = Integer.parseInt(stringToken.nextToken());
            for (int i = 0; i < caseNum; ++i) {
                stringToken = new StringTokenizer(buffer.readLine());
                height = Integer.parseInt(stringToken.nextToken());
                weight = Integer.parseInt(stringToken.nextToken());
                int[][] board = new int[height][weight];

                if (!setBoard(board, height, weight, buffer))
                    System.out.println("0");
                else
                    System.out.println(cover(board));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean setBoard(int[][] board, int height, int weight, BufferedReader buffer) throws IOException {
        int whiteCnt = 0;
        for (int h = 0; h < height; ++h) {
            String line = buffer.readLine();
            for (int w = 0; w < weight; ++w) {
                String data = String.valueOf(line.charAt(w));
                if (data.equals("."))
                    whiteCnt++;
                if (data.equals("#"))
                    board[h][w] = 1;
            }
        }
        return whiteCnt % 3 == 0;
    }

    private static boolean set(int[][] board, int y, int x, int type, int delta){
        boolean ok = true;

        for (int i = 0; i < 3; ++i) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];
            if (ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length)
                ok = false;
            else if ((board[ny][nx] += delta) > 1)
                ok = false;
        }
        return ok;
    }

    private static int cover(int[][] board) {
        int y = -1, x = -1;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (y != -1) break;
        }

        if (y == -1) return 1;

        int ret = 0;
        for (int type = 0; type < 4; ++type) {
            if (set(board, y, x, type, 1))
                ret += cover(board);
            set(board, y, x, type, -1);
        }
        return ret;
    }
}

