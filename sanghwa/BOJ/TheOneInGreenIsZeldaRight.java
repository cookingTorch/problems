package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TheOneInGreenIsZeldaRight {

    static int[][] board;
    static int[][] cost;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int caseNum = 1;
    public static void main(String[] args) throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer token = new StringTokenizer(buffer.readLine());
            int size = Integer.parseInt(token.nextToken());
            if (size == 0) {
                break;
            }
            board = new int[size][size];
            cost = new int[size][size];
            for (int[] ints : cost) {
                Arrays.fill(ints, Integer.MAX_VALUE);
            }
            for (int i = 0; i < size; i++) {
                token = new StringTokenizer(buffer.readLine());
                for (int j = 0; j < size; j++) {
                    board[i][j] = Integer.parseInt(token.nextToken());
                }
            }
            bfs(size);
            caseNum++;
        }
    }

    public static void bfs(int size) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{0, 0, board[0][0]});
        cost[0][0] = board[0][0];

        while (!queue.isEmpty()) {
            Integer[] temp = queue.poll();
            int currY = temp[0];
            int currX = temp[1];
            int currCost = temp[2];

            if (currCost > cost[currY][currX]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int newY = currY + dy[i];
                int newX = currX + dx[i];
                if (isValid(newY, newX, size)) {
                    int newCost = currCost + board[newY][newX];
                    if (cost[newY][newX] > newCost) {
                        cost[newY][newX] = newCost;
                        queue.add(new Integer[]{newY, newX, newCost});
                    }
                }
            }
        }
        System.out.println("Problem " + caseNum + ": " + cost[size - 1][size -1]);
    }

    public static boolean isValid(int y, int x, int size) {
        return (y < size && x < size) && (y >= 0 && x >= 0);
    }
}
