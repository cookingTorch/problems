package AGS.Week2.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheaterSeat {
    static int[] dp = new int[41];
    static int startNum, maxNum, count;
    static ArrayList<Integer> possibleNums = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 좌석의 개수
        int m = Integer.parseInt(br.readLine()); // 고정석(vip)의 개수

        startNum = 1;
        for (int i = 0; i < m; i++) {
            int vipSeat = Integer.parseInt(br.readLine());
            /*
            1. vip가 첫 번째 순서로 나오는 경우
            2. vip가 연달아 나오는 경우
            이러한 경우를 방지하기 위해 vipSeat - startNum = 0이면 1을 넣어준다. (고정석이므로 한 가지 방법밖에 나오지 않아서 1을 넣었습니다)
             */
            if (vipSeat - startNum == 0) {
                possibleNums.add(1);
                startNum = vipSeat + 1; // vip 좌석은 포함하면 안되므로 1을 더해준다.
                continue;
            }
            possibleNums.add(vipSeat - startNum); // 전체에서 vip좌석까지의 구간을 구해 list에 더하는 식
            startNum = vipSeat + 1; // vip 좌석은 포함하면 안되므로 1을 더해준다.
        }
        if (startNum - 1 != n) { // vip가 맨 마지막 칸에 있지 않은 경우, 마지막 vip ~ 끝 까지 계산을 해야한다.
            possibleNums.add(n - startNum + 1); // 마지막 좌석을 포함해야 하므로 1을 더해준다.
        }

        for (int seatNum : possibleNums) { // 가장 긴 연속 좌석길이를 구해서 가장 긴 연속 길이만큼 dp 계산을 하였습니다.
            if (maxNum < seatNum) {
                maxNum = seatNum;
            }
        }
        dp[1] = 1;
        dp[2] = 2;
        if (maxNum >= 3) {
            for (int i = 3; i <= maxNum; i++) {
                dp[i] = dp[i - 1] + dp[i - 2]; // 점화식 입니다.
            }
        }
        count = 1;
        for (int seatNum : possibleNums) {
            count *= dp[seatNum]; // 좌석길이의 가능한 경우의 수를 모두 곱해주는 식입니다.
        }
        System.out.println(count);
    }
}