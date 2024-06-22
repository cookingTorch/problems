package JavaCodingTestStudy.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1520 {
    static int row, col;
    static int[][] arr, dp;
    static int[] mx = { -1, 0, 1, 0 };
    static int[] my = { 0, 1, 0, -1 };
    public static int DFS(int x, int y) {
        if (x == row && y == col) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int dx = x + mx[i];
            int dy = y + my[i];

            if (dx < 1 || dy < 1 || dx > row || dy > col) { //map 벗어남
                continue;
            }
            // arr[x][y]보다 arr[dx][dy]가 높이가 더 낮다면
            // arr[dx][dy]에서 끝점까지 도달하는 경로의 개수를 더한다.
            if (arr[x][y] > arr[dx][dy]) {
                dp[x][y] += DFS(dx, dy);
            }
        }
        return dp[x][y];
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        arr = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= col; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[row + 1][col + 1]; // 도착점으로 가는 경로 개수
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(DFS(1,1));
    }


}
