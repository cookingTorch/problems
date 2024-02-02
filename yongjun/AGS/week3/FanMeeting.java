package AGS.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class FanMeeting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());
        while (testCase-- > 0) {
            String singer = br.readLine();
            String fans = br.readLine();
            System.out.println(KARATSUBA_ALGORITHM(singer, fans));
        }
    }

    static int KARATSUBA_ALGORITHM(String x, String y) {

        String singer = replaceStringToInteger(x);
        String fans = replaceStringToInteger(y);

        BigInteger a = new BigInteger(singer, 2);
        BigInteger b = new BigInteger(fans, 2);

        int fan_range = fans.length();
        int singer_range = singer.length();
        int result = 0;
        for (int i = 0; i <= fan_range-singer_range; i++) {
            if (a.and(b).equals(BigInteger.ZERO)) {
                result++;
            }
            b = b.shiftRight(1);
        }

        return result;
    }

    // 이진수로 변환
    static String replaceStringToInteger(String input) {
        return input.replace("M", "1").replace("F", "0");
    }
}

