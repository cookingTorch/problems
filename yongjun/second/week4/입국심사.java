import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        return binarySearch(1, (long) times[times.length - 1] * n, n, times);
    }
    public long binarySearch(long left, long right, int n, int[] times){
        long answer = 0;
        while (left <= right){
            long mid = (left + right) / 2;

            if(countPerson(mid, times) < n){
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }
        return answer;
    }
    public long countPerson(long mid, int[] times){
        long cnt = 0;
        for(int time : times){
            cnt += (mid / time);
        }
        return cnt;
    }
}