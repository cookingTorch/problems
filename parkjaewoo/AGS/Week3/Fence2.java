package AGS.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

// 성공 (풀이 참조)
public class Fence2 {
    static int[] fence;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = parseInt(br.readLine());

        while (testCase-- > 0) {
            int n = parseInt(br.readLine());
            fence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(divideAndConquer(0, n - 1));
        }
    }
    // 분할 정복
    private static int divideAndConquer(int left, int right) {
        int mid, result, low, high, height;
        if (left == right) { // 모든 분할이 이루어 졌음을 뜻합니다.
            return fence[left];
        }

        mid = (left + right) / 2;
        result = Math.max(divideAndConquer(left, mid), divideAndConquer(mid + 1, right)); // 이진 트리로 분할하는 과정입니다.
        low = mid;
        high = mid + 1;
        height = Math.min(fence[low], fence[high]);
        result = Math.max(result, height * 2); // fence[low]와 fence[high] 중 낮은 height를 구하고, x2한 길이와 큰 길이를 비교하는 과정입니다.

        while (left < low || high < right) { // 다시 조합하는 과정입니다.
            if (high < right && (low == left || fence[low - 1] < fence[high + 1])) { // 더 큰 방향으로 넓혀감을 의미합니다.
                high++;
                height = Math.min(height, fence[high]); // 더 큰 방향으로 넓힐 시, 작은 높이를 구하고 범위를 구할 수 있습니다.
            } else {
                low--;
                height = Math.min(height, fence[low]);
            }
            result = Math.max(result, height * (high - low + 1)); // 앞선 result와 비교하면 값을 갱신합니다.
        }
        return result;
    }
}
