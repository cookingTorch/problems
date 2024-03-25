package BOJ.SecondWeek2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindMine {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int count = 0;
            int sum = 0;
            int[] array;
            char[] mineArray;

            array = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            mineArray = br.readLine().toCharArray();
            for (int i = 0; i < n; i++) {
                if (mineArray[i] == '*') {
                    for (int j = i - 1; j <= i + 1; j++) {
                        if (j < 0 || j > n - 1) {
                            continue;
                        }
                        array[j]--;
                    }
                    count++;
                }
            }

            for (int i = 0; i < n; i++) {
                sum += array[i];
            }
            if (sum % 3 == 0) {
                System.out.println(sum / 3 + count);
            } else {
                System.out.println(sum / 3 + count + 1);
            }
        }
    }
}
