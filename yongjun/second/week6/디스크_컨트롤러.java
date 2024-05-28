package second.week6;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {

        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> jobQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int idx = 0, answer = 0, cnt = 0, end = 0;;
        while(cnt < jobs.length) {
            while(idx < jobs.length && jobs[idx][0] <= end) {
                jobQueue.add(jobs[idx++]);
            }

            if(jobQueue.isEmpty()) {
                end++;
            } else {
                int[] job = jobQueue.poll();
                end += job[1];
                answer += end - job[0];
                cnt++;
            }

        }
        return answer / jobs.length;
    }
}
