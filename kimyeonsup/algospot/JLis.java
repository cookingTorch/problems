package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JLis {
    static long[][] dp;
    static int[] nNumbers;
    static int[] mNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while (testcase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            dp = new long[n + 1][m + 1];
            nNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            mNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], -1);
            }

            System.out.println(getMax(-1, -1));
        }
    }

    public static long getMax(int nStart, int mStart) {

        if(dp[nStart + 1][mStart + 1] != -1) return dp[nStart + 1][mStart + 1];

        long nNumber = nStart == -1 ? Long.MIN_VALUE : nNumbers[nStart];
        long mNumber = mStart == -1 ? Long.MIN_VALUE : mNumbers[mStart];
        long maxNum = Math.max(nNumber, mNumber);

        long result = 0;

        for (int i = nStart + 1; i < nNumbers.length; i++) {
            if(maxNum < nNumbers[i])
                result = Math.max(result, getMax(i, mStart) + 1);
        }
        for (int i = mStart + 1; i < mNumbers.length; i++) {
            if(maxNum < mNumbers[i])
                result = Math.max(result, getMax(nStart, i) + 1);
        }
        dp[nStart + 1][mStart + 1] = result;
        return result;
    }
}
