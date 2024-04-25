package pgs;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class 디스크컨트롤러 {
    public int solution(int[][] jobs) {
        Queue<Job> waiting = new PriorityQueue<>();
        int time = 0;
        int sum = 0;
        int i = 0;

        Arrays.sort(jobs, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        do {
            Job job = waiting.poll();

            if (!Objects.isNull(job)) {
                sum += (time - job.start) + job.time;
                time += job.time;
            }

            while (i < jobs.length && jobs[i][0] <= time) {
                waiting.add(new Job(jobs[i][0], jobs[i][1]));
                i++;
            }

            if (i < jobs.length && waiting.isEmpty()) {
                waiting.add(new Job(jobs[i][0], jobs[i][1]));
                time = jobs[i][0];
                i++;
            }

        } while (!waiting.isEmpty());

        return sum / jobs.length;
    }

    static class Job implements Comparable<Job> {
        int start;
        int time;

        public Job(int start, int time) {
            this.start = start;
            this.time = time;
        }


        @Override
        public int compareTo(Job o) {
            return this.time - o.time;
        }
    }
}
