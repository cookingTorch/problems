package first.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tiling {
    static int[] cache = new int[101];
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(br.readLine());
        dp();
        while (c-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n == 1) {
                sb.append(1).append("\n");
            } else {
                sb.append(cache[n]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void dp() {
        cache[2] = 2;
        cache[3] = 3;
        for (int i = 4; i <= 100; i++) {
            cache[i] = (cache[i - 1] + cache[i - 2]) % MOD;
        }
    }
}

