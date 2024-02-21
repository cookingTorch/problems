package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TripathCnt {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        // dp[i][j] = if arr[i-1][j - 1] == arr[i][j-1] ? dp[i-1][j] + dp[i-1][j] : max(dp[i-1][j - 1], dp[i-1][j])
        /*
        4
        1
        1 1
        1 1 1
        1 1 1 1
        * */
        while (testcase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int j = 0;
                while (st.hasMoreElements()) {
                    int value = Integer.parseInt(st.nextToken());
                    arr[i][j] = value;
                    j++;
                }
            }
            System.out.println(solve(n, arr));
        }
    }

    /*
    pathDp = 경로 dp
    maxValue = 각 행열의 최대 값 dp
    * */
    private static int solve(int n, int[][] arr) {
        int[][] pathDp = new int[n][n];
        int[][] maxValueDp = new int[n][n];

        pathDp[0][0] = 1;
        maxValueDp[0][0] = arr[0][0];

        // dp[i][j] = if arr[i-1][j - 1] == arr[i][j-1] ? dp[i-1][j] + dp[i-1][j] : max(dp[i-1][j - 1], dp[i-1][j])
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                int number = arr[i][j];
                setMaxValue(j, i, maxValueDp, number);
                setPathCount(i, j, pathDp, maxValueDp);
            }
        }

        int maxValue = 0;
        for (int number : maxValueDp[n - 1]) {
            maxValue = Math.max(maxValue, number);
        }

        int sum = 0;
        for (int j = 0; j < n; j++) {
            if (maxValueDp[n - 1][j] == maxValue) {
                sum += pathDp[n - 1][j];
            }
        }
        return sum;
    }

    private static void setPathCount(int i, int j, int[][] pathDp, int[][] maxValueDp) {
        if (j == 0)  {
            pathDp[i][j] = pathDp[i - 1][j];
            return;
        }
        if (j == i) {
            pathDp[i][j] = pathDp[i - 1][j - 1];
            return;
        }

        if (maxValueDp[i - 1][j - 1] == maxValueDp[i - 1][j]) {
            pathDp[i][j] = pathDp[i - 1][j] + pathDp[i - 1][j - 1];
        } else if (maxValueDp[i - 1][j - 1] > maxValueDp[i - 1][j]){
            pathDp[i][j] = pathDp[i - 1][j - 1];
        } else {
            pathDp[i][j] = pathDp[i - 1][j];
        }
    }

    private static void setMaxValue(int j, int i, int[][] maxValueDp, int number) {
        if (j == 0)  {
            maxValueDp[i][j] = number + maxValueDp[i - 1][j];
            return;
        }
        if (j == i)  {
            maxValueDp[i][j] = number +  maxValueDp[i - 1][j - 1];
            return;
        }
        maxValueDp[i][j] = number + Math.max(maxValueDp[i - 1][j - 1], maxValueDp[i - 1][j]);
    }
}
