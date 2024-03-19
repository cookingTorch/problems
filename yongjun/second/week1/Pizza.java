package second.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pizza {
    static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        array = new int[d + 1];
        array[0] = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < d + 1; i++) {
            array[i] = Math.min(array[i - 1], Integer.parseInt(st.nextToken()));
        }

        // 밑에서부터 검사하기 때문에 오븐의 깊이로 length를 설정한다.
        int length = d;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int pizza = Integer.parseInt(st.nextToken());
            //
            while (length > 0 && array[length] < pizza) {
                length--;
            }
            length--;

            if (length == 0 && i != n - 1) {
                break;
            }
        }
        // 밑의 코드가 왜 틀린지 모르겠음.
//        if (length < 0) {
//            System.out.println(0);
//            return;
//        }

        System.out.println(length + 1);

    }
}

