package BOJ.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2302 {
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n명을 입력 받는다
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // m명의 vip를 입력 받는다
        int m = Integer.parseInt(st.nextToken());

        // n이 1일 때를 대비하여 n + 1만큼의 메모리 할당
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // dp의 피보나치 수열을 구한다
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int[] vipMembers = new int[m];

        // vip의 index를 vipMembers에 저장한다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            vipMembers[i] = Integer.parseInt(st.nextToken()) - 1; // 편하게 관리하기 위해 vipIdx - 1로 입력받는다
        }
        // vipMembers의 vipIdx마다 짜른 배열의 길이를 통해 경우의 수를 구해준다
        // 따라서, 짜른 배열의 길이를 구하기 위해 start를 선언한다
        int start = 0;
        answer = 1;
        for (int vipIdx : vipMembers) {
            answer *= dp[vipIdx - start]; // answer에 dp[vipIdx - start]을 곱해주며 dp[짜른 배열의 길이] (경우의수)를 곱해준다.
            start = vipIdx + 1; // start를 vipIdx + 1을 해주어야 갱신된다
        }
        if (m != 0) {
            answer *= dp[n - start]; // 예시 input값을 받을 경우, vipIdx를 토대로 위에서 2번 수행하기 때문에 [총 길이 - 마지막 vipIdx]를 한 번 더 구해주어야 한다.
        } else {
            answer = dp[n]; // 만약 vip의 수가 0일 경우, dp[n - 1]을 구하기만 하면 된다.
        }
        System.out.println(answer);
    }
}
