package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Monitor {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, m, nodeCount, min = Integer.MAX_VALUE;
    static Node[] nodes;
    private static int[][] copyBoard(int[][] board) {
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = board[i][j];
            }
        }
        return map;
    }
    private static boolean isInner(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
    private static int findMin(int[][] board) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    count++;
                }
            }
        }
        return Math.min(min, count);
    }
    private static void typeOne(int _x, int _y, int idx, int[][] map) {
        for (int i = 0; i < 4; i++) {
            int[][] board = copyBoard(map);
            int x = _x + dx[i];
            int y = _y + dy[i];

            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[i];
                y += dy[i];
            }
            dfs(idx + 1, board);
        }
    }
    private static void typeTwo(int _x, int _y, int idx, int[][] map) {
        for (int i = 0; i < 2; i++) {
            int[][] board = copyBoard(map);
            int x = _x + dx[i];
            int y = _y + dy[i];

            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[i];
                y += dy[i];
            }
            x = _x + dx[i + 2];
            y = _y + dy[i + 2];
            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[i + 2];
                y += dy[i + 2];
            }
            dfs(idx + 1, board);

        }
    }
    private static void typeThree(int _x, int _y, int idx, int[][] map) {
        for (int i = 0; i < 4; i++) {
            int[][] board = copyBoard(map);
            int x = _x + dx[i % 4];
            int y = _y + dy[i % 4];

            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[i % 4];
                y += dy[i % 4];
            }
            x = _x + dx[(i + 1) % 4];
            y = _y + dy[(i + 1) % 4];
            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[(i + 1) % 4];
                y += dy[(i + 1) % 4];
            }
            dfs(idx + 1, board);
        }
    }
    private static void typeFour(int _x, int _y, int idx, int[][] map) {
        for (int i = 0; i < 4; i++) {
            int[][] board = copyBoard(map);
            int x = _x + dx[i % 4];
            int y = _y + dy[i % 4];

            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[i % 4];
                y += dy[i % 4];
            }
            x = _x + dx[(i + 1) % 4];
            y = _y + dy[(i + 1) % 4];
            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[(i + 1) % 4];
                y += dy[(i + 1) % 4];
            }
            x = _x + dx[(i + 2) % 4];
            y = _y + dy[(i + 2) % 4];
            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[(i + 2) % 4];
                y += dy[(i + 2) % 4];
            }
            dfs(idx + 1, board);
        }
    }
    private static void typeFive(int _x, int _y, int idx, int[][] map) {
        int[][] board = copyBoard(map);

        for (int i = 0; i < 4; i++) {
            int x = _x + dx[i];
            int y = _y + dy[i];

            while (isInner(x, y)) {
                if (board[x][y] == 6) {
                    break;
                } else if (board[x][y] == 0) {
                    board[x][y] = -1;
                }
                x += dx[i];
                y += dy[i];
            }
        }
        dfs(idx + 1, board);
    }
    private static void dfs(int idx, int[][] board) {
        if (idx == nodeCount) {
            min = findMin(board);
            return;
        }
        int x = nodes[idx].x;
        int y = nodes[idx].y;
        switch (board[x][y]) {
            case 1:
                typeOne(x, y, idx, board);
                break;
            case 2:
                typeTwo(x, y, idx, board);
                break;
            case 3:
                typeThree(x, y, idx, board);
                break;
            case 4:
                typeFour(x, y, idx, board);
                break;
            case 5:
                typeFive(x, y, idx ,board);
                break;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodeCount = 0;
        nodes = new Node[8];
        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] >= 1 && board[i][j] <= 5) {
                    nodes[nodeCount++] = new Node(i, j);
                }
            }
        }

        dfs(0, board);
        System.out.println(min);
    }
}

class Node {
    int x;
    int y;

    Node(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }
}
