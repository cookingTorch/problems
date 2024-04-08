package AGS.SecondWeek4;

import java.util.Arrays;

public class Immigration {
    public static void main(String[] args) {
        System.out.println(Solution.solution(6, new int[] {7, 10}));
    }
}

class Solution {
    public static long isPossible(long value, int[] times) {
        long sum = 0;

        for (int time : times) {
            sum += (value / time);
        }
        return sum;
    }
    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long start = 0;
        long end = times[times.length-1] * (long)n;

        while (start <= end) {
            long mid = (start + end) / 2;
            long tmp = isPossible(mid, times);
            if (tmp < n) {
                start = mid + 1;
            } else {
                answer = mid;
                end = mid - 1;
            }
        }
        return answer;
    }
}
