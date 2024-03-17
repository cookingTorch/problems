package second.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Switch {
    static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int switchNum = Integer.parseInt(st.nextToken());
        array = new int[switchNum];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < switchNum; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int studentsNum = Integer.parseInt(st.nextToken());

        while (studentsNum-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if (type == 1) {
                male(number, switchNum);
            } else {
                female(number, switchNum);
            }
        }
        for (int i = 0; i < switchNum; i++) {
            if ((i + 1) % 20 == 0) {
                sb.append(array[i]).append(" ").append("\n");

            } else {
                sb.append(array[i]).append(" ");
            }
        }
        System.out.println(sb);
    }

    static void male(int x, int switchNum) {
        for (int i = 0; i < switchNum; i++) {
            if ((i + 1) % x == 0) {
                changeState(i);
            }
        }
    }

    static void female(int x, int switchNum) {
        x = x - 1;
        changeState(x);
        int first = x - 1;
        int second = x + 1;

        while (first >= 0 && second < switchNum) {
            if (array[first] == array[second]) {
                changeState(first);
                changeState(second);
                first -= 1;
                second += 1;

            } else {
                break;
            }
        }
    }

    static void changeState(int x) {
        if (array[x] == 1) {
            array[x] = 0;
        } else {
            array[x] = 1;
        }
    }
}