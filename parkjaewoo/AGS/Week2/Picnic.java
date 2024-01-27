package AGS.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Picnic {
    static int n, m, answer;
    static HashMap<Integer, ArrayList<Integer>> map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(st.nextToken());

        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            visited = new boolean[n];
            answer = 0;
            map = new HashMap<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());

                map.computeIfAbsent(num1, k -> new ArrayList<>()).add(num2);
                map.computeIfAbsent(num2, k -> new ArrayList<>()).add(num1);
            }
            backTracking(0);
            sb.append(answer + "\n");
        }
        System.out.print(sb);
        br.close();
    }
    public static int findX() {
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }
    public static void backTracking(int idx) {
        if (idx == n / 2) {
            answer++;
            return;
        }
        int x = findX();
        if (x == -1 || map.get(x) == null) return;

        for (int e : map.get(x)) {
            if (!visited[e]) {
                visited[x] = true;
                visited[e] = true;
                backTracking(idx + 1);
                visited[e] = false;
                visited[x] = false;
            }
        }
    }
}

//            for (int e : map.keySet()) {
//                System.out.println(e);
//                for (Integer s : map.get(e)) {
//                    System.out.print(s + " ");
//                }
//                System.out.println();
//            }

//4
//2 1
//0 1
//4 6
//0 1 1 2 2 3 3 0 0 2 1 3
//6 10
//0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
//10 20
//0 1 0 2 0 3 0 4 1 6 1 8 2 5 2 3 2 4 2 5 3 4 4 5 6 8 8 9 3 4 3 6 5 8 5 9 6 9 2 9