package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AlgoSpot {

    static int[][] board;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());

        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = buffer.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }
        bfs();
    }

    public static void bfs() {
        int[][] dist = new int[N][M];

        for (int[] ints : dist) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        Queue<Integer[]> queue = new LinkedList<>();
        int[] dy = {0, 0, -1, 1};
        int[] dx = {-1, 1, 0, 0};

        queue.add(new Integer[] {0, 0, 0});
        dist[0][0] = 0;

        while (!queue.isEmpty()) {
            Integer[] temp = queue.poll();
            int nowY = temp[0];
            int nowX = temp[1];
            int cost = temp[2];

            for (int i = 0; i < 4; i++) {
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];
                if (!isValid(newY, newX))
                    continue;
                if (board[newY][newX] == 0) {
                    if (dist[newY][newX] > cost) {
                        dist[newY][newX] = cost;
                        queue.add(new Integer[] {newY, newX, cost});
                    }
                } else {
                    if (dist[newY][newX] > cost + 1) {
                        dist[newY][newX] = cost + 1;
                        queue.add(new Integer[]{newY, newX, cost + 1});
                    }
                }
            }
        }
        System.out.println(dist[N - 1][M -1]);
    }

    public static boolean isValid(int y, int x) {
        return (y < N && x < M) && (y >= 0 && x >= 0);
    }
}

