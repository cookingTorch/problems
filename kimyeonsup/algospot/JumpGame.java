package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JumpGame {
    /**
     * 외발 뛰기
     * 맨 왼쪽 위칸 부터 시작
     * 맨 아래쪽 오른 칸이 끝나는 지점
     * 각 칸에 적혀있는 숫자만큼 이동
     * 방향은 아래, 오른쪽
     * 끝지점에 도작할 수 있는 방법이 있는지 확인하는 프로그램
     * YES, NO
     */
    // 복잡도 게산 - 3초
    // 보드 크기 : 2 <= n <= 100
    // 2 * n^2
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bf.readLine());

        while (testCase-- > 0) {
            int boardSize = Integer.parseInt(bf.readLine());
            int[][] board = getBoard(boardSize, bf);
            visited = new int[boardSize][boardSize];

            // dfs 알고리즘으로 탐색한다.
            boolean isSuccess = jumpGame(0, 0, board);
            System.out.println(getResultValue(isSuccess));
        }
    }

    private static String getResultValue(boolean isSuccess) {
        if (isSuccess) {
            return "YES";
        }
        return "NO";
    }

    // board[y][x] = 이동거리
    // 탐색 방향은 두번
    // 탐색시 고려 : board 범위를 벗어나면 return
    // 끝지점 (n-1, n-1) 가 아니면 계속 탐색
    private static boolean jumpGame(int y, int x, int[][] board) {
        // 범위 체크
        int length = board.length - 1;
        if (y < 0 || y > length || x < 0 || x > length) {
            return false;
        }
        if (visited[y][x] == 1) {
            return false;
        }
        visited[y][x] = 1;

        int jump = board[y][x];

        if (jump == 0) {
            return true;
        }

        // 오른쪽 || 아래
        return jumpGame(y, x + jump, board) || jumpGame(y + jump, x, board);
    }

    private static int[][] getBoard(int boardSize, BufferedReader bf) throws IOException {
        int[][] board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            String[] cols = bf.readLine().split(" ");

            for (int j = 0; j < cols.length; j++) {
                board[i][j] = Integer.parseInt(cols[j]);
            }
        }
        return board;
    }
}
