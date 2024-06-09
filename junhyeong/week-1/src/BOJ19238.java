import java.util.*;
import java.io.*;

public class BOJ19238 {
    static int N, M, energy, startX, startY;
    static int[][] map;
    static Info[] infos;
    static int[][] distance;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        energy = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());

        infos = new Info[M + 2];
        for (int i = 2; i <= M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            map[x1][y1] = i;
            infos[i] = new Info(x1, y1, x2, y2);
        }

        for (int i = 0; i < M; i++) {
            ArrayList<Point> candidates = findCandidates();

            if (candidates.size() == 0) {
                System.out.println(-1);
                return;
            }

            Collections.sort(candidates, (o1, o2) -> {
                if (o1.distance == o2.distance) {
                    if (o1.x == o2.x) {
                        return o1.y - o2.y;
                    } else {
                        return o1.x - o2.x;
                    }
                } else {
                    return o1.distance - o2.distance;
                }
            });

            Point selectedPassenger = candidates.get(0);
            map[selectedPassenger.x][selectedPassenger.y] = 0;

            energy -= selectedPassenger.distance;
            startX = selectedPassenger.x;
            startY = selectedPassenger.y;

            int destX = infos[selectedPassenger.index].destX;
            int destY = infos[selectedPassenger.index].destY;

            int moveDistance = movePassenger(destX, destY);

            if (moveDistance == -1) {
                System.out.println(-1);
                return;
            }

            energy -= moveDistance;
            startX = destX;
            startY = destY;
            energy += moveDistance * 2;
        }

        System.out.println(energy);
    }

    private static ArrayList<Point> findCandidates() {
        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(startX, startY, 0));
        visited[startX][startY] = true;

        ArrayList<Point> candidates = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.x;
            int y = current.y;
            int distance = current.distance;

            if (energy - distance < 0) break;
            if (map[x][y] > 1) {
                candidates.add(new Point(map[x][y], x, y, distance));
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (!isInBounds(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, distance + 1));
            }
        }

        return candidates;
    }

    private static int movePassenger(int destX, int destY) {
        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            int x = current.x;
            int y = current.y;
            int distance = current.distance;

            if (energy - distance < 0) {
                return -1;
            }

            if (x == destX && y == destY) {
                return distance;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isInBounds(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, distance + 1));
            }
        }

        return -1;
    }

    private static boolean isInBounds(int x, int y) {
        return x > 0 && x <= N && y > 0 && y <= N;
    }

    static class Node {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static class Info {
        int startX, startY, destX, destY;

        public Info(int startX, int startY, int destX, int destY) {
            this.startX = startX;
            this.startY = startY;
            this.destX = destX;
            this.destY = destY;
        }
    }

    static class Point {
        int index, x, y, distance;

        public Point(int index, int x, int y, int distance) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
