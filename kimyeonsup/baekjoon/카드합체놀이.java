package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 카드합체놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        Queue<Long> queue = new PriorityQueue<>();
        token = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            queue.offer(Long.parseLong(token.nextToken()));
        }

        while (m-- > 0) {
            long sum = queue.poll() + queue.poll();

            queue.offer(sum);
            queue.offer(sum);
        }

        long sum = 0;
        for (Long number : queue) {
            sum += number;
        }

        System.out.println(sum);
    }
}
