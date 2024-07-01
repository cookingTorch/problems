package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ChickenDelivery {
    static int N, M;
    static int[][] graph;
    static ArrayList<int[]> house = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> selected = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(buffer.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(buffer.readLine(), " ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) {
                    house.add(new int[]{i, j});
                } else if (graph[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }
        visited = new boolean[chicken.size()];

        back(0, 0);
        System.out.println(result);
    }

    static void back(int depth, int start) {
        if (depth == M) {
            int sum = 0;
            for (int[] h : house) {
                int min = Integer.MAX_VALUE;
                for (int[] s : selected) {
                    int d = Math.abs(h[0] - s[0]) + Math.abs(h[1] - s[1]);
                    min = Math.min(d, min);
                }
                sum += min;
            }
            result = Math.min(result, sum);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected.add(chicken.get(i));
                back(depth + 1, i + 1);
                selected.remove(selected.size() - 1);
                visited[i] = false;
            }
        }
    }
}
