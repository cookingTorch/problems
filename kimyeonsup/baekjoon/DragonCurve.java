package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DragonCurve {
    private static final int[][] direction = {
            {0, 1}, {-1, 0}, {0, -1}, {1, 0}
    };

    private static int map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[101][101];

        while (n-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            int g = Integer.parseInt(tokenizer.nextToken());

            draw(y, x, getDirection(d, g));
        }

        int count = search();
        System.out.println(count);
    }

    private static int search() {
        int count = 0;
        for (int y = 0; y < map.length - 1; y++) {
            for (int x = 0; x < map[y].length - 1; x++) {
                if (map[y][x] == 1 && map[y + 1][x] == 1 && map[y][x + 1] == 1 && map[y + 1][x + 1] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static List<Integer> getDirection(int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        // 드래곤 커브를 만들기 위해 세대를 반복한다. 0세대 -> g세대
        while (g-- > 0) {
            for (int i = directions.size() - 1; i >= 0; i--) { // 마지막 드래곤 커브 부터 탐색
                directions.add((directions.get(i) + 1) % 4);
            }
        }
        return directions;
    }

    public static void draw(int y, int x, List<Integer> directions) {
        map[y][x] = 1;

        for (int dir : directions) {
            y = direction[dir][0] + y;
            x = direction[dir][1] + x;
            map[y][x] = 1;
        }
    }
}
