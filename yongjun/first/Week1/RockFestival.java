package first.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RockFestival {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int range = Integer.parseInt(st.nextToken());
        for (int i = 0; i < range; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int[] sum = new int[n + 1];
            sum[0] = 0;
            /*
            한 줄을 받은 뒤, token으로 나누면 되므로 for loop 이전에 st를 선언한다.
             */
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                sum[j] = sum[j - 1] + Integer.parseInt(st.nextToken());
            }

            double ans = Double.MAX_VALUE;
            for (int j = 1; j <= n - l + 1; j++) {
                double res = Double.MAX_VALUE;
                for (int k = j + l - 1; k <= n; k++) {
                    double result =  (sum[k] - sum[j - 1]) / (double)(k - j + 1);
                    res = Math.min(result, res);
                }
                ans = Math.min(ans, res);
            }
            System.out.println(ans);
        }
        br.close();
    }
}