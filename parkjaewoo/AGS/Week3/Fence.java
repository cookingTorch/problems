package AGS.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

// 30분 실패 (완탐)
public class Fence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = parseInt(br.readLine());

        while (testCase-- > 0) {
            int n = parseInt(br.readLine());
            int[] fence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int fenceMax = Arrays.stream(fence).max().getAsInt();
            int sizeMax = 0;

            for (int i = 1; i <= fenceMax; i++) {
                int start = 0;
                int lenMax = Integer.MIN_VALUE;
                for (int j = 0; j < n; j++) {
                    if (fence[j] >= i) {
                        start++;
                    } else {
                        start = 0;
                    }
                    lenMax = Math.max(lenMax, start);
                }
                sizeMax = Math.max(sizeMax, lenMax * i);
            }
            System.out.println(sizeMax);
        }
    }
}
