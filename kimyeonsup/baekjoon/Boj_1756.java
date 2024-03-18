package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1756 {
    private static int result = 0;;
    private static int[] ovens;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());

        int ovenNum = Integer.parseInt(token.nextToken());
        int pizzaNum = Integer.parseInt(token.nextToken());
        result = ovenNum;
        ovens = new int[ovenNum + 1];
        ovens[0] = Integer.MAX_VALUE;

        token = new StringTokenizer(buffer.readLine());
        for (int i = 1; i < ovenNum + 1; i++) {
            int diameter = Integer.parseInt(token.nextToken());
            ovens[i] = Math.min(diameter, ovens[i - 1]);
        }

        token = new StringTokenizer(buffer.readLine());
        for (int i = 0; i < pizzaNum; i++) {
            int pizzaDia = Integer.parseInt(token.nextToken());
            updateResult(pizzaDia);
        }

        System.out.println(result);
    }

    public static void updateResult(int pizzaDia) {;
        // pizzaDia 보다 작은 가장 빠른 값 (가장 위에 있는 값)
        for (int i = 1; i <= result; i++) {
            if (ovens[i] < pizzaDia) {
                result = i - 1;
                ovens[i - 1] = 0;
                break;
            }
        }
//        result -= 1;
    }
}

/*
5 3
3 3 3 1 1
3 3 3


2

1
* */
