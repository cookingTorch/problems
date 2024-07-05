package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bf.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        String[] numbers = bf.readLine().split("");

        Deque<Integer> queue = new ArrayDeque<>();

        int count = 0;
        int index = 0;
        for (String s : numbers) {
            int number = Integer.parseInt(s);

            while (!queue.isEmpty() && count < k) {
                if (queue.peek() < number) {
                    queue.pop();
                    count++;
                } else {
                    break;
                }
            }
            index++;
            queue.push(number);
            if (count == k) break;
        }

        for (int i = index; i < numbers.length; i++) {
            queue.push(Integer.valueOf(numbers[i]));
        }

        for (int i = 0; i < n - k; i++) {
            System.out.print(queue.pollLast());
        }
    }
}
