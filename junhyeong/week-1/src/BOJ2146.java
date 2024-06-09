import java.io.*;
import java.util.*;

public class BOJ2146 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n;
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        numbering_map(map, n);
        solve(map, n);
    }

    private static void solve(int[][] map, int n) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 1) {
                    int distance = find_distance(map, map[i][j], i, j, n);
                    if (distance != -1) {
                        min = Math.min(min, distance);
                    }
                }
            }
        }
        System.out.println(min);
    }

    private static int find_distance(int[][] map, int num, int x, int y, int n) {
        if (map[Math.max(x - 1, 0)][y] != 0 &&
                map[x][Math.max(y - 1, 0)] != 0 &&
                map[Math.min(x + 1, map.length - 1)][y] != 0 &&
                map[x][Math.min(y + 1, map.length - 1)] != 0) {
            return -1;
        }

        int[][] distance = new int[n][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        distance[x][y] = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curX = current[0];
            int curY = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (map[nx][ny] == num) continue;
                if (map[nx][ny] > 1 && map[nx][ny] != num) {
                    return distance[curX][curY];
                }
                if (distance[nx][ny] > distance[curX][curY] + 1) {
                    distance[nx][ny] = distance[curX][curY] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }

    private static void numbering_map(int[][] map, int n) {
        int number = 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    bfs(map, i, j, number, n);
                    number++;
                }
            }
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    private static void bfs(int[][] map, int x, int y, int num, int n) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = num;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curX = current[0];
            int curY = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (map[nx][ny] == 0 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                map[nx][ny] = num;
                q.add(new int[]{nx, ny});
            }
        }
    }
}
