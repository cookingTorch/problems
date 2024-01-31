import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class FanMeeting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String members = br.readLine().replace('F','0').replace('M','1');
            String fans = br.readLine().replace('F','0').replace('M','1');

            int n = members.length();
            int m = fans.length();

            BigInteger membersBinary = new BigInteger(members,2); //비트가 20만개이므로 BigInteger
            BigInteger currFansBinary = new BigInteger(fans,2);

            int shakingHands = 0;

            for (int i = 0; i < m - n + 1; i++) {
                if (!currFansBinary.and(membersBinary).equals(BigInteger.ZERO)) {  //비트연산 10001 & 11111 != 0 [남자-남자 -> 1]
                    shakingHands++;
                }
                currFansBinary = currFansBinary.shiftRight(1); //팬 이동
            }
            System.out.println(m - n + 1 - shakingHands);
        }
    }
}
