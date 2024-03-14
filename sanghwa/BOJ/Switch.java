package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Switch {
    static int MAN = 1;
    static int switchNum = 0;
    static int studentNum = 0;
    static int[] switchArray;
    static int[][] studentArray;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());

        switchNum = Integer.parseInt(token.nextToken());
        switchArray = new int[switchNum];
        token = new StringTokenizer(buffer.readLine(), " ");
        for (int i = 0; i < switchNum; ++i) {
            switchArray[i] = Integer.parseInt(token.nextToken());
        }
        token = new StringTokenizer(buffer.readLine());
        studentNum = Integer.parseInt(token.nextToken());
        studentArray = new int[studentNum][2];

        for (int i = 0; i < studentNum; ++i) {
            token = new StringTokenizer(buffer.readLine());
            studentArray[i][0] = Integer.parseInt(token.nextToken());
            studentArray[i][1] = Integer.parseInt(token.nextToken());
        }
        for (int i = 0; i < studentNum; i++) {
            if (studentArray[i][0] == MAN)
                manAct(studentArray[i][1]);
            else
                womanAct(studentArray[i][1]);
        }

        for (int i = 0; i < switchNum; ++i) {
            System.out.print(switchArray[i]);
            if ((i + 1) % 20 == 0)
                System.out.println();
            else
                System.out.print(" ");
        }
    }

    static void manAct(int num) {
        for (int i = num - 1; i < switchNum; i += num) {
            switchArray[i] = switchArray[i] ^ 1;
        }
    }

    static void womanAct(int num) {
        if (num == 1 || num == switchNum) {
            switchArray[num - 1] = switchArray[num - 1] ^ 1;
            return;
        }
        int numIdx = num - 1;
        switchArray[numIdx] = switchArray[numIdx] ^ 1;
        for (int i = 1; i < switchNum / 2; i++) {
            if (numIdx + i < switchNum && numIdx - i >= 0 && switchArray[numIdx + i] == switchArray[numIdx - i]) {
                switchArray[numIdx - i] = switchArray[numIdx - i] ^ 1;
                switchArray[numIdx + i] = switchArray[numIdx + i] ^ 1;
            } else {
                break;
            }
        }
    }
}
