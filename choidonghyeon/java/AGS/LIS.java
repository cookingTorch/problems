import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] sequence = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] dp = new int[N];
            dp[0] = 1;

            for (int i = 1; i < N; i++) {
                dp[i] = 1;
                for (int j = 0; j < i ; j++) {
                    if (sequence[i] > sequence[j]) {
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
            }

            System.out.println(Arrays.stream(dp).max().getAsInt());
        }
    }
}
