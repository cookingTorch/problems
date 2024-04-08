package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_16401 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int joka = Integer.parseInt(stringTokenizer.nextToken());
        int bar = Integer.parseInt(stringTokenizer.nextToken());
        int[] bars = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(bars);

        int result = searchSnackBar(1, bars[bars.length - 1], bars, joka);

        System.out.println(result);

        br.close();
    }

    public static int searchSnackBar(int start, int end, int[] bars, int joka) {
        int mid = (start + end) / 2;
        int cnt = 0;

        if(start > end) {
            return mid;
        }

        for (int i = 0; i < bars.length; i++) {
            if(bars[i] - mid >= 0) {
                cnt += bars[i] / mid;
            }
        }

        if (cnt >= joka) return searchSnackBar(mid + 1, end, bars, joka);
        else return searchSnackBar(start, mid - 1, bars, joka);
    }
}
