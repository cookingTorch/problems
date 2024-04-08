package PGS.Immigration;

import java.util.Arrays;

public class Immigration {
    public static void main(String[] args) {
        int n = 4;
        int[] times = {1, 1, 1};
        long result = solution(n, times);
        System.out.println(result);
    }
    public static long solution(int n, int[] times) {
        int length = times.length;
        Arrays.sort(times);

        long maxTime = (long) times[length - 1] * n;
        long minTime = 1;
        long answer = maxTime;
        while (minTime <= maxTime) {
            long midTime = (minTime + maxTime) / 2;
            long cntN = 0;

            for (int time : times) {
                cntN += midTime / time;
                if (cntN >= n)
                    break;
            }
            if (cntN < n) {
                minTime = midTime + 1;
            } else {
                answer = Math.min(answer, midTime);
                maxTime = midTime - 1;
            }
        }
        return answer;
    }
}
