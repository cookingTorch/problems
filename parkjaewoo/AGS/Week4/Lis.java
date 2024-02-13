package AGS.Week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// 아직 풀지 못했습니다.
public class Lis {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int c = parseInt(br.readLine());

        while (c-- > 0) {
            int n = parseInt(br.readLine());
            if (n <= 0) {
                System.out.println(0);
                continue;
            }
            int[] array = new int[n];
            int max = 1, answer = 1;
            int compareValue;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                array[i] = parseInt(st.nextToken());
            }

            compareValue = array[0];

            for (int i = 1; i < n; i++) {
                if (compareValue < array[i]) {
                    max++;
                    if (answer < max) {
                        answer = max;
                    }
                } else {
                    max = 1;
                }
                compareValue = array[i];
            }
            System.out.println(answer);
        }
    }
}
