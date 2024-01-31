import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 분할정복 (책 참고)
 * 실행속도 : 228ms
 */
public class Main {
    static int N;
    static int[] boards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            boards = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                boards[i] = Integer.parseInt(st.nextToken());
            }
            
            sb.append(calculateMaxArea(0, N - 1)).append("\n");
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int calculateMaxArea(int left, int right) {
        if (left == right) {
            return boards[left];
        }

        int mid = (left + right) / 2;
        int result = Math.max(calculateMaxArea(left, mid), calculateMaxArea(mid + 1, right));

        int leftBound = mid;
        int rightBound = mid;
        int height = boards[mid];

        while (left < leftBound || rightBound < right) {
            if (left == leftBound || rightBound < right && boards[leftBound - 1] <= boards[rightBound + 1]) {
                rightBound++;
                height = Math.min(height, boards[rightBound]);
            } else {
                leftBound--;
                height = Math.min(height, boards[leftBound]);
            }
            result = Math.max(result, (rightBound - leftBound + 1) * height);
        }
        return result;
    }
}
