package AGS.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fence {
    static int[] fence;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            fence = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                fence[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println(solve(0, n - 1));

        }
    }

    static int solve(int left, int right) {
        // 기저사례 : left와 right의 값이 같으면 더 이상 진행할 수 없으므로 left 값을 반환
        if (left == right) {
            return fence[left];
        }
        int mid = (left + right) / 2;
        // 분할 정복을 통해 절반으로 나눈 값 중, 더 큰 값을 선택한다.
        int maxValue = Math.max(solve(left, mid), solve(mid + 1, right));
        // mid, mid + 1의 길이를 가지고 있는 사각형을 고려한다.
        // 더 작은 값에 2를 곱해서 직사각형을 구한다.
        int low = mid;
        int high = mid + 1;
        int height = Math.min(fence[low], fence[high]);
        // 분할정복을 통해 나온 값과 mid에 걸친 값과 비교해서 큰 값을 반환한다.
        maxValue = Math.max(maxValue, height * 2);

        while (low > left || high < right) {
            // 항상 높이가 더 높은 쪽으로 확장한다.
            if (high < right && (low == left || fence[high + 1] > fence[low - 1])) {
                high += 1;
                height = Math.min(height, fence[high]);
            } else {
                low -= 1;
                height = Math.min(height, fence[low]);
            }
            maxValue = Math.max(maxValue, height * (high - low + 1));
        }
        return maxValue;
    }
}