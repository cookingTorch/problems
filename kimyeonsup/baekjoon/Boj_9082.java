package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_9082 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int total = 0;
            int length = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split("");
            for (int i = 0; i < length; i++) {
                total += Integer.parseInt(input[i]);
            }
            br.readLine();
            System.out.println((total + 2) / 3);
        }
    }
}
