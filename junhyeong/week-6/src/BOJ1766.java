import java.io.*;
import java.util.*;

public class BOJ1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }

        int[] cnt = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            cnt[b]++;
        }

        for (int i = 1; i <= n; i++) {
            if (cnt[i] == 0) {
                pq.offer(i);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            int num = pq.poll();
            res.add(num);

            for (int c : arr.get(num)) {
                cnt[c]--;

                if (cnt[c] == 0) {
                    pq.offer(c);
                }
            }
        }

        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            if (i != res.size() - 1) {
                System.out.print(" ");
            }
        }
    }
}
