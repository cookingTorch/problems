import java.util.*;

public class Main {
    static final int MAX = 100;

    static int[][] triangle = new int[MAX][MAX];

    static int[][] cache = new int[MAX][MAX];

    static int[][] countCache = new int[MAX][MAX];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt(); // 테스트 케이스의 수

        for (int t = 0; t < c; t++) {
            int n = scanner.nextInt(); // 삼각형의 크기

            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    cache[i][j] = -1;
                    countCache[i][j] = -1;
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    triangle[i][j] = scanner.nextInt();
                }
            }
            System.out.println(count(n, 0, 0));
        }
    }

    // 각 위치에서의 최대 경로를 구한다.
    static int path(int n, int y, int x) {
        if (y == n - 1) return triangle[y][x];
        if (cache[y][x] != -1) return cache[y][x];
        return cache[y][x] = triangle[y][x] + Math.max(path(n, y + 1, x), path(n, y + 1, x + 1));
    }

    // 최대 경로의 개수를 구한다.
    static int count(int n, int y, int x) {
        if (y == n - 1) return 1;
        if (countCache[y][x] != -1) return countCache[y][x];
        int ret = 0;
        if (path(n, y + 1, x + 1) >= path(n, y + 1, x)) ret += count(n, y + 1, x + 1);
        if (path(n, y + 1, x + 1) <= path(n, y + 1, x)) ret += count(n, y + 1, x);
        return countCache[y][x] = ret;
    }
}
