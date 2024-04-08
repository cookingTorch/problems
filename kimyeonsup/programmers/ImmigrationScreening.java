package pgs;

import java.util.Arrays;

public class ImmigrationScreening {
    public static void main(String[] args) {
        int n = 4;
        int[] times = {1, 1, 1};

        long solution = new ImmigrationScreening().solution(n, times);
        System.out.println(solution);
    }

    public long solution(int n, int[] times) {
        Arrays.sort(times);
        return binarySearch(times[0], times[times.length - 1] * (long) n, times, n, 0);
    }

    private long binarySearch(long start, long end, int[] times, int n, long result) {
        long mid = (start + end) / 2;

        if (start > end) {
            return result;
        }

        long count = 0;
        for (int i = 0; i < times.length; i++) {
            count += mid / times[i];
        }

        if (count >= n) {
            return binarySearch(start, mid - 1, times, n, mid);
        } else {
            return binarySearch(mid + 1, end, times, n, mid);
        }
    }
}
