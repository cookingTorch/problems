package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 과제 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Assignment> list = new ArrayList<>();
        int n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = bf.readLine().split(" ");
            list.add(new Assignment(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        list.sort(((o1, o2) -> o2.score - o1.score));

        int[] time = new int[1001];
        for (int i = 0; i < n; i++) {

            Assignment assignment = list.get(i);
            for (int j = assignment.time; j > 0 ; j--) {
                if (time[j] == 0) {
                    time[j] = assignment.score;
                    break;
                }
            }
        }

        int sum = 0;
        for (int score : time) {
            sum += score;
        }
        System.out.println(sum);
    }

    static class Assignment {
        int time;
        int score;

        public Assignment(int time, int score) {
            this.time = time;
            this.score = score;
        }
    }
}
