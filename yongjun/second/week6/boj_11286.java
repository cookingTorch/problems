package second.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(st.nextToken());
        PriorityQueue<Number> pq = new PriorityQueue<>();
        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());

            if (input == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(pq.poll().number).append("\n");
                }
                continue;
            }
            pq.add(new Number(input));
        }
        System.out.println(sb);
    }

    static class Number implements Comparable<Number> {
        public Number(int number) {
            this.number = number;
        }

        int number;
        @Override
        public int compareTo(Number o) {
            if (Math.abs(this.number) == Math.abs(o.number)) {
                return this.number - o.number;
            }
            return Math.abs(this.number) - Math.abs(o.number);
        }
    }
}