package BOJ;

import java.io.*;
import java.util.*;

public class SurveillanceOptimization {
    private static class CCTV {
        int x, y, type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    private static final int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    private static final int[] dy = {0, 1, 0, -1};
    private static List<CCTV> cctvs = new ArrayList<>();
    private static int N, M, minBlindArea = Integer.MAX_VALUE;
    private static int[][] office;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, office);
        System.out.println(minBlindArea);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] >= 1 && office[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, office[i][j]));
                }
            }
        }
    }

    private static void dfs(int index, int[][] map) {
        if (index == cctvs.size()) {
            calculateBlindArea(map);
            return;
        }

        CCTV currentCCTV = cctvs.get(index);
        int[][] copiedMap;
        for (int dir = 0; dir < 4; dir++) {
            copiedMap = copyMap(map);
            updateSurveillanceArea(copiedMap, currentCCTV, dir);
            dfs(index + 1, copiedMap);
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, M);
        }
        return newMap;
    }

    private static void updateSurveillanceArea(int[][] map, CCTV cctv, int direction) {
        int[][] directions = {
                {0}, // 1번 CCTV는 이 경우에 사용되지 않음
                {direction}, // 2번 CCTV
                {direction, (direction + 1) % 4}, // 3번 CCTV
                {direction, (direction + 1) % 4, (direction + 2) % 4}, // 4번 CCTV
                {0, 1, 2, 3} // 5번 CCTV, 모든 방향을 감시
        };

        for (int dir : directions[cctv.type - 1]) {
            int x = cctv.x;
            int y = cctv.y;

            while (true) {
                x += dx[dir];
                y += dy[dir];

                if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 6) break;
                if (map[x][y] == 0) map[x][y] = 7;
            }
        }
    }

    private static void calculateBlindArea(int[][] map) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) count++;
            }
        }
        minBlindArea = Math.min(minBlindArea, count);
    }
}
