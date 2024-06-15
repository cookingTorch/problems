package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class 수열의점수 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Queue<Long> positive = new PriorityQueue<>((o1, o2) -> (int) (Math.abs(o2) - Math.abs(o1)));
        Queue<Long> negative = new PriorityQueue<>();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long number = Long.parseLong(bf.readLine());
            if (number > 0) {
                positive.add(number);
            } else {
                negative.add(number);
            }
        }

        while (!positive.isEmpty()) {
            if (positive.size() > 1) {
                Long number = positive.poll();
                Long next = positive.poll();
                if (number == 1 || next == 1) {
                    sum += number + next;
                } else {
                    sum += number * next;
                }
            } else {
                sum += positive.poll();
            }
        }

        while (!negative.isEmpty()) {
            if (negative.size() > 1) {
                Long number = negative.poll();
                Long next = negative.poll();
                sum += number * next;
            } else {
                sum += negative.poll();
            }
        }

        System.out.println(sum);
    }
}
