package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 알고스팟 {
    static int n, m;
    static int[][] dist;
    static int[][] board;
    static int[][] dir = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());

        board = new int[m][n];
        dist = new int[m][n];


        for (int start = 0; start < m; start++) {
            String[] input = bf.readLine().split("");

            for (int end = 0; end < n; end++) {
                board[start][end] = Integer.parseInt(input[end]);
                dist[start][end] = Integer.MAX_VALUE;
            }
        }

        int result1 = 0;
        dijstra(0, 0);

        System.out.println(dist[m - 1][n - 1]);
    }
    private static void dijstra(int y, int x) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(y, x, 0));

        boolean[][] visited = new boolean[m][n];
        dist[y][x] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            visited[cur.y][cur.x] = true;

            for (int[] d : dir) {
                int newY = d[0] + cur.y;
                int newX = d[1] + cur.x;
                if (!(newY >= 0 && newY < m && newX >= 0 && newX < n)) {
                    continue;
                }
                if (visited[newY][newX]) continue;

                int cnt = cur.t;
                if (board[newY][newX] == 1) cnt++;
                if (dist[newY][newX] > cnt) {
                    dist[newY][newX] = cnt;
                    queue.add(new Node(newY, newX, cnt));
                }
            }
        }
    }

    private static class Node implements Comparable<Node>{
        int y, x, t;

        public Node(int y, int x, int t) {
            this.y = y;
            this.x = x;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            return this.t - o.t;
        }
    }
}
