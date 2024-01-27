package AGS.Week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ClockSync {
    static final int INF = 10000;
    static int[][] switches = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };
    static int min = Integer.MAX_VALUE;
    static int[] clocks;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());

        clocks = new int[16];

        while (c-- > 0) {
            clocks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            min = recursive(0);
            System.out.println(min != INF ? min : -1);
        }
    }
    public static int recursive(int idx) {
        if (idx == 10) {
            return isAll12() ? 0 : INF;
        }
        int tmp = INF;
        for (int i = 0; i < 4; i++) {
            tmp = Math.min(tmp, recursive(idx + 1) + i);
            click(idx);
        }
        return tmp;
    }
    public static void click(int idx) {
        for (int e : switches[idx]) {
            clocks[e] += 3;
            if (clocks[e] == 15)
                clocks[e] = 3;
        }
    }
    public static boolean isAll12() {
        for (int i = 0; i < 16; i++) {
            if (clocks[i] != 12)
                return false;
        }
        return true;
    }
}
