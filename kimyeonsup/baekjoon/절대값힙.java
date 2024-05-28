package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class 절대값힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());

        Queue<Number> queue = new PriorityQueue<>();
        while (n-- > 0) {
            int number = Integer.parseInt(br.readLine());

            if (number == 0) {
                Number minNumber = queue.poll();
                sb.append(Objects.isNull(minNumber) ? 0 : minNumber.number);
                sb.append("\n");
            } else {
                queue.add(Number.create(number));
            }
        }

        System.out.println(sb.toString());
    }

    static class Number implements Comparable<Number>{
        int number;

        public Number(int number) {
            this.number = number;
        }

        static Number create(int number) {
            return new Number(number);
        }

        @Override
        public int compareTo(Number o) {
            if (Math.abs(this.number) < Math.abs(o.number)) {
                return -1;
            }
            if (Math.abs(this.number) == Math.abs(o.number)) {
                return this.number - o.number;
            }
            return Math.abs(this.number) - Math.abs(o.number);
        }
    }
}
