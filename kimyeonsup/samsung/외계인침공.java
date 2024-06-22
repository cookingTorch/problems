package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외계인침공 {
    public static void main(String args[]) throws Exception
    {
		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(bf.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for (int test = 1; test <= T; test++) {
            int n = Integer.parseInt(bf.readLine());
            long[] arr = new long[n + 1];
            long[] dp = new long[n + 1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dp[1] = arr[1];
            dp[2] = Math.max(arr[1], arr[2]);
            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 1]);
            }

            System.out.println("#" + test + " " + dp[n]);
        }
    }
}
