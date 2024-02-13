package AGS.Week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class JumpGame {
    static int[][] board;
    static boolean[][] visited;
    static boolean answer;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    private static boolean isInnerBoard(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
    private static void dfs(Node node, int n) {
        if (node.x == n - 1 && node.y == n - 1) {
            answer = true;
        }
        for (int i = 0; i < 2; i++) {
            int x = node.x + (dx[i] * board[node.x][node.y]);
            int y = node.y + (dy[i] * board[node.x][node.y]);
            if (isInnerBoard(x, y, n) && !visited[x][y]) {
                visited[x][y] = true;
                dfs(new Node(x, y), n);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int c = parseInt(br.readLine());
        while (c-- > 0) {
            int n = parseInt(br.readLine());
            board = new int[n][n];
            visited = new boolean[n][n];
            answer = false;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = parseInt(st.nextToken());
                }
            }
            dfs(new Node(0, 0), n);
            if (answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
class Node {
    int x;
    int y;

    Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
