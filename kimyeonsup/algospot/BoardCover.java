package jmb;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
3
3 7
#.....#
#.....#
##...##
3 7
#.....#
#.....#
##..###
8 10
##########
#........#
#........#
#........#
#........#
#........#
#........#
##########
* */

public class BoardCover {

    private static int[][] board;
    private static int[][][] coverType = {
            {{0, 0}, {1, 0} , {0, 1}},
            {{0, 0}, {0, 1} , {1, 1}},
            {{0, 0}, {1, 0} , {1, 1}},
            {{0, 0}, {1, 0} , {1, -1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testcase = parseInt(bf.readLine());

        while (testcase-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());
            int boardYSize = Integer.parseInt(stringTokenizer.nextToken());
            int boardXSize = Integer.parseInt(stringTokenizer.nextToken());
            board = new int[boardYSize][boardXSize];

            for (int i = 0; i < boardYSize; i++) {
                char[] boardCols = bf.readLine().toCharArray();
                for (int j = 0; j < boardXSize; j++) {
                    if (boardCols[j] == '#') {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = 0;
                    }
                }
            }

            System.out.println(cover());
        }
    }

    private static int cover() {
        int y = -1;
        int x = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (y != -1) break;
        }
        if (y == -1) return 1;

        int result = 0;
        for (int type = 0; type < 4; type++) {
            if (set(y, x, type, 1)) {
                result += cover();
            }
            set(y, x, type, -1);
        }
        return result;
    }

    private static boolean set(int y, int x, int type, int set) {
        boolean isTrue = true;

        for (int i = 0; i < 3; i++) {
            int coverY = y + coverType[type][i][0];
            int coverX = x + coverType[type][i][1];
            if(isRange(coverY, coverX)) {
                isTrue = false;
            } else if ((board[coverY][coverX] += set) > 1) {
                isTrue = false;
            }
        }
        return isTrue;
    }

    private static boolean isRange(int coverY, int coverX) {
        return coverY < 0 || coverY >= board.length || coverX < 0 || coverX >= board[0].length;
    }
}
