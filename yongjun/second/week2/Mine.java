package second.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());
        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] numbers = new int[n];
            char[] bombs = new char[n];
            String input;
            input = br.readLine();
            for (int i = 0; i < n; i++) {
                numbers[i] = input.charAt(i) - '0';
            }

            int result = 0;

            input = br.readLine();
            for (int i = 0; i < n; i++) {
                char bomb = input.charAt(i);
                if (bomb == '*') {
                    if (i == 0) {
                        firstPlace(i, numbers);
                    } else if (i == n - 1) {
                        lastPlace(i, numbers);
                    } else {
                        middlePlace(i, numbers);
                    }
                    result++;
                }
                bombs[i] = bomb;
            }

            for (int i = 0; i < n; i++) {
                if (i == 0 && numbers[i] > 0 && numbers[i + 1] > 0 && bombs[i] == '#') {
                    firstPlace(i, numbers);
                    result++;
                } else if (i == n - 1 && numbers[i] > 0 && numbers[i - 1] > 0 && bombs[i] == '#') {
                    lastPlace(i, numbers);
                    result++;
                } else if (i > 0 && i < n - 1 && numbers[i] > 0 && numbers[i - 1] > 0 && numbers[i + 1] > 0 && bombs[i] == '#') {
                    middlePlace(i, numbers);
                    result++;
                }
            }
            System.out.println(result);
        }
    }

    static void firstPlace(int idx, int[] nums) {
        nums[idx]--;
        nums[idx + 1]--;
    }

    static void middlePlace(int idx, int[] nums) {
        nums[idx - 1]--;
        nums[idx + 1]--;
        nums[idx]--;
    }

    static void lastPlace(int idx, int[] nums) {
        nums[idx]--;
        nums[idx - 1]--;
    }
}