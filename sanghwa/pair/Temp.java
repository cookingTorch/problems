import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int y;
        int x;
        int cost;
        int currentDirection;

        public Node(int y, int x, int cost, int currentDirection) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.currentDirection = currentDirection;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static char[][] board;
    static ArrayList<Node> targets = new ArrayList<>();
    static int[][][] visited;

    // 상좌하우 : 0, 1, 2, 3
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int w, h;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken()); // 가로
        h = Integer.parseInt(st.nextToken()); // 세로

        board = new char[h][w];
        for (int i = 0; i < h; i++) {
            String input = br.readLine();
            for (int j = 0; j < w; j++) {
                char ipt = input.charAt(j);
                board[i][j] = ipt;
                if (ipt == 'C') {
                    targets.add(new Node(i, j, -1, -1));
                }
            }
        }
        System.out.println(dijkstra());

    }

    static int dijkstra() {
        Node destination = targets.get(1);

        visited = new int[h][w][4];
        for (int[][] ints : visited) {
            for (int[] array : ints) {
                Arrays.fill(array, w * h);
            }
        }
//        visited[targets.get(0).y][targets.get(0).x] = 0;
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        Node start = targets.get(0);

        for (int i = 0; i < 4; i++) {
            nodeQueue.add(new Node(start.y, start.x, 0, i));
        }

        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.poll();

            if (node.y == destination.y && node.x == destination.x) {
                return node.cost;
            }

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + node.y;
                int nx = dx[i] + node.x;

                if (!isInMap(ny, nx)) {
                    continue;
                }

                if ((node.currentDirection + 2) % 4 == i) {
                    continue;
                }

                if (!canMove(ny, nx)) {
                    if (node.currentDirection != i) {
                        if (visited[ny][nx] >= node.cost + 1) {
                            visited[ny][nx] = node.cost + 1;
                            nodeQueue.add(new Node(ny, nx, node.cost + 1, i));
                        }
                    } else {
                        if (visited[ny][nx] >= node.cost) {
                            visited[ny][nx] = node.cost;
                            nodeQueue.add(new Node(ny, nx, node.cost, i));
                        }
                    }
                }
            }
        }
        return 0;
    }

    static boolean isInMap(int y, int x) {
        return (y < h && x < w) && (y >= 0 && x >= 0);
    }

    static boolean canMove(int y, int x) {
        return board[y][x] == '*';
    }

}