package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DragonCurve {

    static int curveNum;
    static int x, y, d, g;
    static int[][] map;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        curveNum = Integer.parseInt(buffer.readLine());
        map = new int[101][101];

        for(int i=1; i<=curveNum; i++) {
            token = new StringTokenizer(buffer.readLine(), " ");

            x = Integer.parseInt(token.nextToken());
            y = Integer.parseInt(token.nextToken());
            d = Integer.parseInt(token.nextToken());
            g = Integer.parseInt(token.nextToken());

            dragonCurve(x, y, d, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    static void dragonCurve(int x, int y, int d, int g) {
        List<Integer> list = new ArrayList<>();
        list.add(d);

        for (int i = 1; i <= g; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add((list.get(j) + 1) % 4);
            }
        }

        map[y][x] = 1;
        for(Integer target : list) {
            x += dx[target];
            y += dy[target];
            map[y][x] = 1;
        }
    }

}
