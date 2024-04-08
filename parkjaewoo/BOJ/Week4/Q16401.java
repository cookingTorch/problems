package BOJ.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q16401 {
    private static int countPossible(int[] snacks, int value) {
        int sum = 0;

        for (int snack : snacks) {
            sum += (snack / value);
        }
        return sum;
    }
    private static int binarySearch(int[] snacks, int people, int last) {
        int result = 0;
        int start = 1;
        int end = snacks[last - 1];

        while (start <= end) {
            int mid = (start + end) / 2;
            if (mid == 0) {
                break;
            }
            if (countPossible(snacks, mid) >= people) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] snacks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(snacks);
        System.out.println(binarySearch(snacks, n, m));
    }
}
