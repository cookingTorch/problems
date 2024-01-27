import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoardCover {
    static int H;
    static int W;
    static char[][] board;
    static boolean[][] visited;
    static int whites;
    static int answer;
    static int cnt;
    static int[][][] blocks = {{{0, 0}, {1, 0}, {0, 1}}, {{0, 0}, {0, 1}, {1, 1}}, {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            board = new char[H][W];
            whites = 0;

            for (int y = 0; y < H; y++) {
                String line = br.readLine();
                for (int x = 0; x < W; x++) {
                    board[y][x] = line.charAt(x);
                    if (board[y][x] == '.') {
                        whites++;
                    }
                }
            }

            if (whites == 0) {
                System.out.println(1);
                continue;
            }

            if (whites % 3 != 0) {
                System.out.println(0);
                continue;
            }
            answer = 0;
            cnt = 0;
            visited = new boolean[H][W];
            solve();

            System.out.println(answer);
        }
    }

    public static void solve() {
        if (cnt == whites) {
            answer += 1;
            return;
        }

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (board[y][x] == '#' || visited[y][x]) {
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    if (!blockable(y, x, i)) {
                        continue;
                    }
                    changeBlockVisited(y, x, i, true);
                    solve();
                    changeBlockVisited(y, x, i, false);
                }
                return;
            }
        }
    }

    private static boolean blockable(int y, int x, int type) {
        for (int[] block : blocks[type]) {
            int my = y + block[0];
            int mx = x + block[1];

            if (!inRange(my, mx) || visited[my][mx] || board[my][mx] == '#') {
                return false;
            }
        }
        return true;
    }

    private static void changeBlockVisited(int y, int x, int type, boolean visitedMark) {
        for (int[] block : blocks[type]) {
            int my = y + block[0];
            int mx = x + block[1];

            visited[my][mx] = visitedMark;
        }
        if (visitedMark) {
            cnt += 3;
        } else {
            cnt -= 3;
        }
    }

    private static boolean inRange(int y, int x) {
        return 0 <= y && y < H && 0 <= x && x < W;
    }
}
