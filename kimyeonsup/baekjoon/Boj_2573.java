package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node3 {
    int x, y;

    public Node3(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Boj_2573 {
    static ArrayList<Node3> list = new ArrayList<>();
    static int[][] board;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int n, m, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        // 입력 값 받기
        // list는 탐색해야할 좌표값
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(st.nextToken());
                board[i][j] = input;
                if (input == 0) {
                    list.add(new Node3(i, j));
                }
            }
        }
        /*
        1. 인접한 0의 개수를 구해야함
        2. 덩어리 구분 방법
         */
        result = 0;
        do {
            meltsIce(List.copyOf(list));
            result += 1;
        } while (!isEnd());
        System.out.println(result);
    }

    //
    static void meltsIce(List<Node3> ices) {
        for (Node3 Node3 : ices) {
            int x = Node3.x;
            int y = Node3.y;
            for (int i = 0; i < dx.length; i++) {
                int currentX = x + dx[i];
                int currentY = y + dy[i];

                // 배열 범위안에 있고, 상하좌우가 0이고, 기존에 빙하였던 것
                if (isRange(currentY, currentX) && board[currentY][currentX] > 0) {
                    board[currentY][currentX] -= 1;
                    if (board[currentY][currentX] == 0) {
                        list.add(new Node3(currentY, currentX));
                    }
                }
            }
        }

    }

    static boolean isEnd() {
        boolean[][] visited = new boolean[n][m];
        int count = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (!visited[y][x] && board[y][x] > 0) {
                    if (++count > 1) {
                        return true;
                    }
                    dfs(y, x, visited);
                }
            }
        }
        if (count == 0) {
            result = 0;
            return true;
        }
        return false;
    }

    static void dfs(int y, int x, boolean[][] visited) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int currentY = y + dy[i];
            int currentX = x + dx[i];

            if (isRange(currentY, currentX) && board[currentY][currentX] > 0 && !visited[currentY][currentX]) {
                dfs(currentY, currentX, visited);
            }
        }
    }

    static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < n && x < m;
    }
}