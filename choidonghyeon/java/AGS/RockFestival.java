/**
 * https://algospot.com/judge/submission/recent/
 * 문제 이름 : Rock Festival
 * 소요 시간(정답까지) : 60분
 * 사용 알고리즘 : 누적합, 구현
 * 문제 핵심 : float, double 연산시 부동 소수점 차이에 의한 정밀도 오차 발생에 주의
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RockFestival {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int[] costs = new int[L];

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                costs[j] = Integer.parseInt(st2.nextToken());
            }
            sol(L, N, costs);
        }
        br.close();
    }

    public static void sol(int L, int N, int[] costs) {
        //길이가 N이상인 연속 부분수열의 평균값의 최소값을 구하는 문제.
        double min_avg = Double.MAX_VALUE;

        int[] prefixSum = new int[L + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < L + 1; i++) {
            prefixSum[i] = costs[i - 1] + prefixSum[i - 1];
        }

        for (int i = N; i < L + 1; i++) {
            for (int j = 0; j < L - i + 1; j++) {
                double avg = (double) (prefixSum[j + i] - prefixSum[j]) / i;
                min_avg = Double.min(min_avg, avg);
            }
        }
        System.out.printf("%.11f\n",min_avg);
    }
}
