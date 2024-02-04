package AGS.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class IceBerg {
    static int[][] board;
    static int n, m;
    static ArrayList<Node> list = new ArrayList<>();
    static ArrayList<Node> removeList = new ArrayList<>();
    static boolean[][] zero;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(st.nextToken());
                board[i][j] = input;
                if (input > 0) {
                    list.add(new Node(j, i));
                }
            }
        }
        /*
        1. 인접한 0의 개수를 구해야함
        2. 덩어리 구분 방법
         */
        int cnt = 0;
        while (true) {
            zero = new boolean[n][m];
            visited = new boolean[n][m];
            if (list.isEmpty()) {
                System.out.println("0");
                return;
            }
            int firstX = list.get(0).x;
            int firstY = list.get(0).y;
            dfs(firstY, firstX);

            if (isEnd()) {
                break;
            }
            cnt += 1;
            for (Node node : list) {
                int x = node.x;
                int y = node.y;
                for (int i = 0; i < dx.length; i++) {

                    int currentX = x + dx[i];
                    int currentY = y + dy[i];

                    if (isRange(currentY, currentX) && !zero[currentY][currentX]) {
                        int ice = board[currentY][currentX];
                        if (ice <= 0) {
                            board[y][x] -= 1;
                            if (board[y][x] <= 0) {
                                removeList.add(node);
                                zero[y][x] = true;
                                break;
                            }
                        }
                    }
                }
            }
            for (Node node : removeList) {
                list.remove(node);
            }
        }
        System.out.println(cnt);
    }

    static boolean isEnd() {
        for (int y = n - 1; y >= 0; y--) {
            for (int x = m - 1; x >= 0; x--) {
                if (board[y][x] > 0 && !visited[y][x]) {
                    return true;
                }
            }
        }
        return false;
    }


    static void dfs(int y, int x) {
        visited[y][x] = true;

        int currentX, currentY;
        for (int i = 0; i < 4; i++) {
            currentY = y + dy[i];
            currentX = x + dx[i];
            if (isRange(currentY, currentX) && (board[currentY][currentX] > 0) && !visited[currentY][currentX]) {
                dfs(currentY, currentX);
            }
        }
    }

    static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < n && x < m;
    }
}