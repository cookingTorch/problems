package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 젤다 {
    static int[][] board;
    static int[][] dist;
    static int n;
    static int[][] direction = {
            {0, 1}, {1, 0}, {-1, 0}, {0, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        int pNum = 0;

        do {
            pNum++;
            board = new int[n][n];
            dist = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer token = new StringTokenizer(bf.readLine());
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(token.nextToken());
                }
            }
            dijstra(0, 0);

            System.out.println("Problem " + pNum + ": " + dist[n - 1][n - 1]);
        } while ((n = Integer.parseInt(bf.readLine())) != 0);
    }

    private static void dijstra(int y, int x) {
        Queue<Node> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];
        dist[y][x] = board[y][x];
        queue.add(new Node(y, x, board[y][x]));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            visited[cur.y][cur.x] = true;

            for (int[] dir : direction) {
                int newY = cur.y + dir[0];
                int newX = cur.x + dir[1];

                if (!(newY >= 0 && newY < n && newX >= 0 && newX < n)) continue;
                if (visited[newY][newX]) continue;

                if (dist[newY][newX] > dist[cur.y][cur.x] + board[newY][newX]) {
                    dist[newY][newX] = dist[cur.y][cur.x] + board[newY][newX];
                    queue.add(new Node(newY, newX, dist[newY][newX]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int y, x, w;

        public Node(int y, int x, int w) {
            this.y = y;
            this.x = x;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
