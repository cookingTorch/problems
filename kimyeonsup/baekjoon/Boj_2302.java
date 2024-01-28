package boj;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 극장 좌석
public class Boj_2302 {

    private static int[] vipNums;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numberCount = parseInt(bf.readLine());
        int vipNumberCount = parseInt(bf.readLine());
        vipNums = new int[vipNumberCount];

        for (int num = 0; num < vipNumberCount; num++) {
            vipNums[num] = parseInt(bf.readLine());
        }

        System.out.println(getNumberOfCases(numberCount));

        bf.close();
    }

    private static int getNumberOfCases(int numberCount) {
        int[] dpRecord = new int[numberCount + 1];
        dpRecord[0] = 1;
        dpRecord[1] = 1;

        for (int number = 2; number <= numberCount; number++) {
            if (!isVip(number) || !isVip(number - 1)) {
                dpRecord[number] = dpRecord[number - 1];
                continue;
            }
            dpRecord[number] = dpRecord[number - 1] + dpRecord[number - 2];
        }
        return dpRecord[numberCount];
    }

    private static boolean isVip(int number) {
        return Arrays.stream(vipNums).noneMatch(vip -> number == vip);
    }   
    
}
