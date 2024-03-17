package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_15683 {

    private static List<CCTV> cctvs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] boardSize = br.readLine().split(" ");
        cctvs = new ArrayList<>();
        int y = Integer.parseInt(boardSize[0]);
        int x = Integer.parseInt(boardSize[1]);
        int[][] board = new int[y][x];

        for (int i = 0; i < y; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                initCCTV(i, j, board);
            }
        }

        System.out.println(backtracking(board, 0));
    }

    private static void initCCTV(int i, int j, int[][] board) {
        switch (board[i][j]) {
            case 1:
                cctvs.add(new OneCCTV(i, j, board));
                break;
            case 2:
                cctvs.add(new TwoCCTV(i, j, board));
                break;
            case 3:
                cctvs.add(new ThreeCCTV(i, j, board));
                break;
            case 4:
                cctvs.add(new FourCCTV(i, j, board));
                break;
            case 5:
                cctvs.add(new FiveCCTV(i, j, board));
                break;
        }
    }

    private static int backtracking(int[][] board, int n) {
        int result = Integer.MAX_VALUE;
        // 기저 사례 - 방향 모두 선택
        if (n >= cctvs.size()) {
            return getCount(board);
        }

        for (int i = 0; i < 4; i++) {
            CCTV cctv = cctvs.get(n);
            if (!(cctv instanceof FiveCCTV)) {
                cctv.saveAreas(board, i);
            }
            result = Math.min(result, backtracking(board, n + 1));
        }

        return result;
    }

    private static int getCount(int[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean isArea = false;
                for (CCTV cctv : cctvs) {
                    if (cctv.isArea(i, j)) {
                        isArea = true;
                        break;
                    }
                }

                if (board[i][j] == 0 && !isArea) {
                    count++;
                }
            }
        }
        return count;
    }
}

abstract class CCTV {

    protected static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    protected int y, x;
    protected Set<Pos> areas;

    public CCTV(int y, int x, int[][] board) {
        this.y = y;
        this.x = x;
    }

    abstract void saveAreas(int[][] board, int direction);

    public boolean isArea(int y, int x) {
        return areas.contains(new Pos(y, x));
    }
}

class Pos {
    int y, x;

    public Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object obj) {
        Pos target = (Pos) obj;
        return y == target.y && x == target.x;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(y + "" + x);
    }
}

class OneCCTV extends CCTV {
    public OneCCTV(int y, int x, int[][] board) {
        super(y, x, board);
    }

    @Override
    void saveAreas(int[][] board, int direction) {
        areas = new HashSet<>();
        int areaY = y;
        int areaX = x;
        int dirY = directions[direction][0];
        int dirX = directions[direction][1];
        int newY = areaY + dirY;
        int newX = areaX + dirX;

        while (newY >= 0 && newY < board.length
                && newX >= 0 && newX < board[0].length) {

            if (board[newY][newX] == 6) {
                break;
            }

            areas.add(new Pos(newY, newX));

            newY += dirY;
            newX += dirX;
        }
    }
}

class TwoCCTV extends CCTV {
    public TwoCCTV(int y, int x, int[][] board) {
        super(y, x, board);
    }

    @Override
    void saveAreas(int[][] board, int direction) {
        areas = new HashSet<>();
        int areaY = y;
        int areaX = x;

        for (int i = 0; i <= 2; i += 2) {
            int dir = (direction + i) % 4;
            int dirY = directions[dir][0];
            int dirX = directions[dir][1];
            int newY = areaY + dirY;
            int newX = areaX + dirX;

            while (newY >= 0 && newY < board.length
                    && newX >= 0 && newX < board[0].length) {

                if (board[newY][newX] == 6) {
                    break;
                }

                areas.add(new Pos(newY, newX));

                newY += dirY;
                newX += dirX;
            }
        }
    }
}

class ThreeCCTV extends CCTV {
    public ThreeCCTV(int y, int x, int[][] board) {
        super(y, x, board);
    }

    @Override
    void saveAreas(int[][] board, int direction) {
        areas = new HashSet<>();
        int areaY = y;
        int areaX = x;

        for (int i = 0; i < 2; i++) {
            int dir = (direction + i) % 4;
            int dirY = directions[dir][0];
            int dirX = directions[dir][1];
            int newY = areaY + dirY;
            int newX = areaX + dirX;

            while (newY >= 0 && newY < board.length
                    && newX >= 0 && newX < board[0].length) {

                if (board[newY][newX] == 6) {
                    break;
                }

                areas.add(new Pos(newY, newX));

                newY += dirY;
                newX += dirX;
            }
        }
    }
}

class FourCCTV extends CCTV {
    public FourCCTV(int y, int x, int[][] board) {
        super(y, x, board);
    }

    @Override
    void saveAreas(int[][] board, int direction) {
        areas = new HashSet<>();
        int areaY = y;
        int areaX = x;

        for (int i = -1; i <= 1; i++) {
            int dir = direction + i;
            if (dir <= -1) dir = 3;
            dir = dir % 4;
            int dirY = directions[dir][0];
            int dirX = directions[dir][1];
            int newY = areaY + dirY;
            int newX = areaX + dirX;

            while (newY >= 0 && newY < board.length
                    && newX >= 0 && newX < board[0].length) {

                if (board[newY][newX] == 6) {
                    break;
                }

                areas.add(new Pos(newY, newX));

                newY += dirY;
                newX += dirX;
            }
        }
    }
}

class FiveCCTV extends CCTV {
    public FiveCCTV(int y, int x, int[][] board) {
        super(y, x, board);
        saveAreas(board, 0);
    }

    @Override
    void saveAreas(int[][] board, int direction) {
        areas = new HashSet<>();
        int areaY = y;
        int areaX = x;

        for (int i = 0; i < 4; i++) {
            int dir = direction + i;
            int dirY = directions[dir][0];
            int dirX = directions[dir][1];
            int newY = areaY + dirY;
            int newX = areaX + dirX;

            while (newY >= 0 && newY < board.length
                    && newX >= 0 && newX < board[0].length) {

                if (board[newY][newX] == 6) {
                    break;
                }

                areas.add(new Pos(newY, newX));

                newY += dirY;
                newX += dirX;
            }
        }
    }
}
