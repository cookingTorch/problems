package second.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 과자_나눠주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken()); // 조카의 수
        int n = Integer.parseInt(st.nextToken()); // 막대 과자의 수
        int[] snacks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬을 할 필요가 없음.
        // 오히려 정렬을 하지 않고 과자 길이의 최대값인 1_000_000_000을 대입하면 시간이 500ms로 줄어드는 모습을 보임.
        System.out.println(binarySearch(snacks, m, 1, 1_000_000_000));
    }

    public static int binarySearch(int[] array, int m, int left, int right) {
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int number : array) {
                cnt += number / mid;
            }
            if (cnt < m) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }
        return result;
    }
}