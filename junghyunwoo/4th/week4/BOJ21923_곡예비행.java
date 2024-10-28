import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 곡예 비행 / Gold 4 / 30m
 * - 468 ms
 * - DP
 * - up[i][j] : (i, j)에 도착하는 상승비행 최대 점수
 * - up[i][j] = max(up[i + 1][j], up[i][j - 1]) + map[i][j]
 * - down[i][j] : (i, j)부터 시작하는 하강비행 최대 점수
 * - down[i][j] = max(down[i + 1][j], down[i][j + 1]) + map[i][j]
 * - up[i][j] + down[i][j] 최대값 출력
 * */
public class BOJ21923_곡예비행 {
    private static final int MIN = Integer.MIN_VALUE >> 1;

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int max;
        int[][] up;
        int[][] map;
        int[][] down;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m + 1];
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 1; j <= m; j++) { // 점수 입력
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        up = new int[n + 1][m + 1];
        down = new int[n + 1][m + 2];
        if (m > 1) {
            up[n][m] = MIN; // 아래쪽 마지막 칸 벽
            down[n][1] = MIN; // 아래쪽 첫 번째 칸 벽
        }
        for (i = 2; i < m; i++) { // 아래쪽 2 ~ M 벽
            up[n][i] = MIN;
            down[n][i] = MIN;
        }
        for (i = n - 1; i >= 0; i--) {
            up[i][0] = MIN; // 왼쪽 벽
            for (j = 1; j <= m; j++) { // (i, j)에 도착하는 상승비행 최대 점수
                up[i][j] = Math.max(up[i + 1][j], up[i][j - 1]) + map[i][j];
            }
        }
        max = MIN;
        for (i = n - 1; i >= 0; i--) {
            down[i][m + 1] = MIN; // 오른쪽 벽
            for (j = m; j > 0; j--) { // (i, j)부터 시작하는 하강비행 최대 점수
                down[i][j] = Math.max(down[i + 1][j], down[i][j + 1]) + map[i][j];
                max = Math.max(max, up[i][j] + down[i][j]); // 상승 비행 + 하강 비행 최대값
            }
        }
        System.out.print(max);
    }
}
