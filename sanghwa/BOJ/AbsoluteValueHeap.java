package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class AbsoluteValueHeap {

    public static class Node implements Comparable<Node> {
        int value;
        int absValue;

        public Node(int value) {
            this.value = value;
            this.absValue = Math.abs(value);
        }

        @Override
        public int compareTo(Node o) {
            if (this.absValue == o.absValue) {
                return this.value - o.value;
            } else {
                return this.absValue - o.absValue;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(buffer.readLine());
        PriorityQueue<Node> queue = new PriorityQueue<>();
        while (cnt != 0) {
            cnt--;
            int newInt = Integer.parseInt(buffer.readLine());
            if (newInt == 0) {
                if (queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    Node temp = queue.poll();
                    System.out.println(temp.value);
                }
            } else {
                queue.offer(new Node(newInt));
            }
        }
    }
}
