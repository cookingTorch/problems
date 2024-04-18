package second.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_4485 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int testCase = Integer.parseInt(st.nextToken());
            if (testCase == 0) {
                return;
            }

            int[][] array = new int[testCase][testCase];
            for (int i = 0; i < testCase; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < testCase; j++) {
                    array[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Problem ").append(cnt++).append(": ").append(bfs(array, testCase));
            System.out.println(sb);
        }
    }

    static int bfs(int[][] array, int testCase) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[testCase][testCase];
        int[][] cost = new int[testCase][testCase];

        for (int[] c : cost) {
            Arrays.fill(c, Integer.MAX_VALUE);
        }
        cost[0][0] = array[0][0];
        queue.add(new Node(0, 0, array[0][0]));

        int result = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.y][node.x] = true;
            result += node.value;
            if (node.x == testCase - 1 && node.y == testCase - 1) {
                return node.value;
            }

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + node.y;
                int nx = dx[i] + node.x;

                if (isOnBoard(ny, nx, testCase) && !visited[ny][nx]) {
                    if (cost[ny][nx] > node.value + array[ny][nx]) {
                        cost[ny][nx] = cost[node.y][node.x] + array[ny][nx];
                        queue.add(new Node(ny, nx, cost[ny][nx]));
                    }
                }
            }
        }
        return result;
    }

    static boolean isOnBoard(int y, int x, int testCase) {
        return y >= 0 && x >= 0 && y < testCase && x < testCase;
    }
    static class Node implements Comparable<Node> {
        int y;
        int x;
        int value;

        public Node(int y, int x, int value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}