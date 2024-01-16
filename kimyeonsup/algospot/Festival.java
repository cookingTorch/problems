package jmb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Festival {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bf.readLine());

        for (int count = 0; count < testCase; count++) {
            String[] inputs = bf.readLine().split(" ");
            int dayCount = Integer.parseInt(inputs[0]);
            int teamCount = Integer.parseInt(inputs[1]);
            int[] pays = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            double min = 1001;

            for (int searchCount = teamCount; searchCount <= dayCount; searchCount++) {
                int minSum = Integer.MAX_VALUE;
                for (int payIndex = 0; payIndex <= pays.length - searchCount; payIndex++) {
                    int sum = 0;
                    for (int i = payIndex; i < payIndex + searchCount; i++) {
                        sum += pays[i];
                    }
                    minSum = Math.min(minSum, sum);
                }

                min = Math.min(min, (double) minSum / searchCount);
            }
            System.out.println(String.format("%.12f", min));
        }

        bf.close();
    }

}
