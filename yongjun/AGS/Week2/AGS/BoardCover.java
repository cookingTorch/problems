package AGS.Week2.AGS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoardCover {
    static String[][] board = new String[51][51];
    static int c, h, w;
    //블록이 움직이는 경우의 수를 총 4가지로 잡았습니다.
    static int[][][] block = {{{0, 0}, {0, 1}, {1, 1}}, {{0, 0}, {0, 1}, {1, 0}},
            {{0, 0}, {1, 0}, {1, 1}}, {{0, 0}, {1, 0}, {1, -1}},};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            for (int j = 0; j < h; j++) {
                String col = br.readLine();
                for (int k = 0; k < w; k++) {
                    board[j][k] = String.valueOf(col.charAt(k));
                }
            }
            System.out.println(play());
        }
    }

    public static int play() {
        boolean isComplete = false;
        int x = -1;
        int y = -1;
        // 비어있는 칸 ( . )을 찾는 반복문
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j].equals(".")) {
                    x = j;
                    y = i;
                    isComplete = true;
                    break;
                }
            }
            if (isComplete) {
                break;
            }
        }
        if (!isComplete) {
            return 1;
        }
        // 블록이 움직일 수 있는 경우의 수가 4가지 이므로 0 ~ 3까지의 반복문
        int res = 0;
        for (int i = 0; i < 4; i++) {
            // 빈 공간이라고 판단되는 칸에 블록을 넣어본 다음에 블록이 들어갈 수 있으면 색을 칠한다.
            if (check(x, y, i)) {
                paint(x, y, i, true);
                //재귀함수를 통해 블록을 덮은 뒤의 가능한 경우의 수를 구한다.
                res += play();
                //색칠한 곳을 다시 제거한다.
                paint(x, y, i, false);
            }

        }
        return res;
    }

    // 블록의 칸 수는 총 3칸이므로 0 ~ 2까지의 반복문
    public static boolean check(int x, int y, int idx) {
        for (int i = 0; i < 3; i++) {
            int dx = x + block[idx][i][1];
            int dy = y + block[idx][i][0];

            // 블록이 보드 밖으로 나가거나 이미 색칠된 곳이라면 false를 반환 한다.
            if (dx < 0 || dx >= w || dy < 0 || dy >= h || board[dy][dx].equals("#")) {
                return false;
            }
        }
        return true;
    }

    // 색을 칠하는 메서드
    public static void paint(int x, int y, int idx, boolean isPaint) {
        if (isPaint) {
            for (int i = 0; i < 3; i++) {
                int dx = x + block[idx][i][1];
                int dy = y + block[idx][i][0];
                board[dy][dx] = "#";
            }
        } else {
            for (int i = 0; i < 3; i++) {
                int dx = x + block[idx][i][1];
                int dy = y + block[idx][i][0];
                board[dy][dx] = ".";
            }
        }
    }
}