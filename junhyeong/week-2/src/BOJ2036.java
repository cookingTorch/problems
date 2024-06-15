import java.util.*;
import java.io.*;

public class BOJ2036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long sum = 0L;
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> negative = new ArrayList<>();
        ArrayList<Integer> positive = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a <= 0)
                negative.add(a);
            else
                positive.add(a);
        }
        Collections.sort(negative);
        positive.sort(Collections.reverseOrder());

        // negative list
        for (int i = 0; i < negative.size(); i+=2) {
            int a = negative.get(i);
            int b = i + 1 < negative.size() ? negative.get(i+1) : 1;
            if (b == 1) {
                sum += a;
                break;
            }
            sum += (long) a * b;
        }

        // positive list
        for (int i = 0; i < positive.size(); i+=2) {
            int a = positive.get(i);
            int b = i + 1 < positive.size() ? positive.get(i+1) : 0;

            if (b == 0) {
                sum += a;
                continue;
            }
            if (a == 1 || b == 1) {
                sum += a;
                sum += b;
                continue;
            }
            sum += (long) a * b;
        }

        System.out.println(sum);
    }
}
