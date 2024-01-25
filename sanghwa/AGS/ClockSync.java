package AGS;

// https://www.algospot.com/judge/problem/read/CLOCKSYNC
// 풀이는 책 참고 (p.168)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ClockSync {
    static int INF = 9999, SWITCHES = 10, CLOCKS = 16;
    static String[] linked = {
            "xxx.............",
            "...x...x.x.x....",
            "....x.....x...xx",
            "x...xxxx........",
            "......xxx.x.x...",
            "x.x...........xx",
            "...x..........xx",
            "....xx.x......xx",
            ".xxxxx..........",
            "...xxx...x...x.." };
    public static void main(String[] args) {
        int caseNum;

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringToken = new StringTokenizer(buffer.readLine());

            caseNum = Integer.parseInt(stringToken.nextToken());
            for (int i = 0; i < caseNum; ++i) {
                stringToken = new StringTokenizer(buffer.readLine());
                int[] clocks = new int[CLOCKS];
                for (int j = 0; j < CLOCKS; ++j) {
                    int time = Integer.parseInt(stringToken.nextToken());
                    clocks[j] = time;
                }
                if (areAligned(clocks)) {
                    System.out.println(0);
                    return;
                }
                int ret = solve(clocks, 0);
                if (ret == INF)
                    ret = -1;
                System.out.println(ret);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void push(int[] clocks, int swtch) {
        for (int clock = 0; clock < CLOCKS; ++clock) {
            if (linked[swtch].charAt(clock) == 'x') {
                clocks[clock] += 3;
                if (clocks[clock] == 15) clocks[clock] = 3;
            }
        }
    }

    private static boolean areAligned(int[] clocks) {
        for (int i = 0; i < CLOCKS; ++i) {
            if (clocks[i] != 12) return false;
        }
        return true;
    }

    private static int solve(int[] clocks, int swtch) {
        if (swtch == SWITCHES) return areAligned(clocks) ? 0 : INF;

        int ret = INF;
        for (int cnt = 0; cnt < 4; ++cnt) {
            ret = Math.min(ret, cnt + solve(clocks, swtch + 1));
            push(clocks, swtch);
        }
        return ret;
    }
}
