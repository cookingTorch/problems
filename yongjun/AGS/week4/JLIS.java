package AGS.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JLIS {
    static int[][] cache;
    static int[] aArray;
    static int[] bArray;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(st.nextToken());
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            aArray = new int[n];
            bArray = new int[m];
            cache = new int[n + 1][m + 1];

            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    cache[i][j] = -1;
                }
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                aArray[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                bArray[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(getMax(-1, -1) - 2);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int getMax(int a, int b) {
        if (cache[a + 1][b + 1] != -1) {
            return cache[a + 1][b + 1];
        }

        long am = (a == -1 ? Long.MIN_VALUE : (long) aArray[a]);
        long bm = (b == -1 ? Long.MIN_VALUE : (long) bArray[b]);

        long maxnum = Math.max(am, bm);

        int sum = 2;
        for (int i = a + 1; i < aArray.length; i++) {
            if (maxnum < aArray[i]) {
                sum = Math.max(sum, getMax(i, b) + 1);
            }
        }

        for (int i = b + 1; i < bArray.length; i++) {
            if (maxnum < bArray[i]) {
                sum = Math.max(sum, getMax(a, i) + 1);
            }
        }
        cache[a + 1][b + 1] = sum;
        return sum;
    }
}
