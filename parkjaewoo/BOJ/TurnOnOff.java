package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TurnOnOff {
    static final int MAN = 1;
    static final int WOMAN = 2;
    static int[] switchStatus;
    public static void setMan(int switchNum, int getSwitch) {
        for (int i = 1; i < switchNum + 1; i++) {
            if (i % getSwitch == 0) {
                if (switchStatus[i - 1] == 1) {
                    switchStatus[i - 1] = 0;
                } else {
                    switchStatus[i - 1] = 1;
                }
            }
        }
    }
    public static void setWoman(int switchNum, int getSwitch) {
        int left = getSwitch - 1;
        int right = getSwitch - 1;

        while (left >= 0 && right < switchNum) {
            if (switchStatus[left] == switchStatus[right]) {
                left--;
                right++;
            } else {
                break;
            }
        }
        left++;
        right--;

        for (int i = left; i <= right; i++) {
            if (switchStatus[i] == 1) {
                switchStatus[i] = 0;
            } else {
                switchStatus[i] = 1;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int switchNum, studentsNum, manOrWoman, getSwitch;

        switchNum = Integer.parseInt(br.readLine());
        switchStatus = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        studentsNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < studentsNum; i++) {
            st = new StringTokenizer(br.readLine());
            manOrWoman = Integer.parseInt(st.nextToken());
            getSwitch = Integer.parseInt(st.nextToken());
            if (manOrWoman == MAN) {
                setMan(switchNum, getSwitch);
            } else if (manOrWoman == WOMAN){
                setWoman(switchNum, getSwitch);
            }
        }

        for (int i = 1; i < switchNum + 1; i++) {
            System.out.print(switchStatus[i - 1] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }
}