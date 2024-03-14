package first.Week1;

import java.io.*;
import java.util.StringTokenizer;

public class Boggle {
    static char[][] array;
    static String[] missionArray;
    static boolean[][][] booleans;
    static int[] xMove = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] yMove = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());

        array = new char[6][6];
        while (c-- > 0) {
            for (int i = 1; i < 6; i++) {
                String alphabet = br.readLine();
                for (int j = 1; j < 6; j++) {
                    array[i][j] = alphabet.charAt(j - 1);
                }
            }

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            missionArray = new String[n];
            for (int i = 0; i < n; i++) {
                missionArray[i] = br.readLine();
            }

            for (int i = 0; i < n; i++) {
                booleans = new boolean[6][6][missionArray[i].length() + 1];
                boolean result = false;
                for (int j = 1; j < 6; j++) {
                    for (int k = 1; k < 6; k++) {
                        if (missionDP(j, k, missionArray[i], 0)) {
                            result = true;
                        }
                    }
                }
                if (result) {
                    bw.write(missionArray[i] + " YES");
                } else {
                    bw.write(missionArray[i] + " NO");
                }
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean missionDP(int x, int y, String missionWord, int index) {
        if (!canMove(x, y)) {
            return false;
        }
        /*
        booleans[x][y][index] -> true 인 경우는 이미 계산된 경우임.
         */
        if (booleans[x][y][index]) {
            return false;
        }
        if (array[x][y] != missionWord.charAt(index)) {
            return false;
        }
        if (missionWord.length() - 1 == 1) {
            return true;
        }
        /*
        index가 끝까지 가면 true 반환
         */
        if (index == missionWord.length() - 1) {
            return true;
        }
        booleans[x][y][index] = true;

        for (int i = 0; i < 8; i++) {
            int newX = x + xMove[i];
            int newY = y + yMove[i];
            if (missionDP(newX, newY, missionWord, index + 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canMove(int x, int y) {
        return (x <= 5 && x >= 1 && y <= 5 && y >= 1);
    }
}
