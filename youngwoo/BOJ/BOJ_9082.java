import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9082 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numTestCases = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int testCase = 0; testCase < numTestCases; testCase++) {
            // 각 테스트 케이스 처리
            int digitSum = 0; // 숫자들의 합
            int numDigits = Integer.parseInt(br.readLine()); // 숫자 개수

            String inputLine = br.readLine(); // 숫자 문자열 입력
            for (int i = 0; i < inputLine.length(); i++) {
                // 숫자 문자열 각 자리의 숫자 값 합산
                digitSum += Character.getNumericValue(inputLine.charAt(i));
            }

            // 빈 줄 처리 (필수는 아님)
            br.readLine();

            // 숫자들의 평균 계산 및 출력
            int average = (digitSum + 2) / 3;
            System.out.println(average);
        }
    }
}

