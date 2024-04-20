package second.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1261 {
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static char[][] array;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        array = new char[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            array[i] = br.readLine().toCharArray();
        }
        System.out.println(bfs());

    }

    public static int bfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.y == array.length - 1 && node.x == array[0].length - 1) {
                return node.value;
            }

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (!isOnBoard(ny, nx)) {
                    continue;
                }

                if (!visited[ny][nx] && array[ny][nx] == '1') {
                    queue.add(new Node(ny, nx, node.value + 1));
                    visited[ny][nx] = true;
                }
                if (!visited[ny][nx] && array[ny][nx] == '0') {
                    queue.add(new Node(ny, nx, node.value));
                    visited[ny][nx] = true;
                }
            }
        }
        return 0;
    }

    public static boolean isOnBoard(int y, int x) {
        return y >= 0 && x >= 0 && y < array.length && x < array[0].length;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int value;

        public Node(int y, int x, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public Node(int y, int x) {
            this.x = x;
            this.y = y;
            this.value = 0;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}