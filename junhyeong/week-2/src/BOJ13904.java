import java.util.*;
import java.io.*;

public class BOJ13904 {
    static int max_day = 1001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(max_day);
        for (int i = 0; i < max_day; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(d).add(w);
        }

        // 각각 정렬 ( 같은날짜에 가장 높은 w가 앞에 오도록 )
        for (int i = 1; i < max_day; i++) {
            list.get(i).sort(Collections.reverseOrder());
        }

        // 같은 날짜에 과제가 여러 개 있을 경우, 다른날에 w가 더 작다면 그날과 교체함
        for (int i = 1; i < max_day; i++) {
            for (int j = 1; j < list.get(i).size(); j++) {
                int a = list.get(i).get(j);
                int min_ind = -1;
                int min_w = 101;
                for (int k = 1; k < i; k++) {
                    if (list.get(k).isEmpty()) {
                        min_ind = k;
                        break;
                    } else if (list.get(k).get(0) < a && list.get(k).get(0) < min_w) {
                        min_ind = k;
                        min_w = list.get(k).get(0);
                    }
                }
                if (min_ind != -1) {
                    if (!list.get(min_ind).isEmpty()) {
                        list.get(min_ind).set(0, a);
                    } else {
                        list.get(min_ind).add(a);
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 1; i < max_day; i++) {
            if (!list.get(i).isEmpty()) {
                sum += list.get(i).get(0);
            }
        }

        System.out.println(sum);
    }
}
