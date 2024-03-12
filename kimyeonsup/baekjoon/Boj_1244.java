package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1244 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] sw = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sw[i] = Integer.parseInt(st.nextToken());
        }

        Converter converter = new Converter(sw);

        int count = Integer.parseInt(br.readLine());
        while (count-- > 0) {
            st = new StringTokenizer(br.readLine());
            converter.convert(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(converter);
    }

    private static class Converter {

        int[] sw;

        public Converter(int[] sw) {
            this.sw = sw;
        }

        private void convert(int gender, int given) {
            if (gender == 1) {
                changeByM(given);
            } else if (gender == 2) {
                changeByF(given);
            }
        }


        // 남자 변환
        private void changeByM(int given) {
            for (int i = given; i < sw.length; i += given) {
                sw[i] = sw[i] == 1 ? 0 : 1;
            }
        }

        // 여자 변환
        private void changeByF(int given) {
            int i = 0;
            while (given - (i + 1) >= 1 && given + (i + 1) < sw.length) {
                i++;
                if (sw[given - i] != sw[given + i]) {
                    i--;
                    break;
                }
            }

            for (int j = given - i; j <= given + i; j++) {
                sw[j] = sw[j] == 1 ? 0 : 1;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < sw.length; i++) {
                sb.append(sw[i]);
                if (i % 20 == 0) {
                    sb.append("\n");
                } else {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
    }
}
