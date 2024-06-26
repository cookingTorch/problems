import java.io.*;
import java.util.*;

public class BOJ1941 {
    static char[][] map = new char[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < 5; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 25c7 -> 조합으로 모든 경우의 수 탐색
        for (int i = 24; i >= 6; i--)
            dfs(0, i, 0, 0, new ArrayList<>());
        System.out.println(ans);
    }

    static int ans = 0;
    private static void dfs(int depth, int num, int S, int Y, ArrayList<Integer> list) {
        list.add(num);

        if (map[num / 5][num % 5] == 'S') S++;
        else Y++;

        if (Y >= 4) return;
        if (depth == 6) {
//            System.out.println(list);
            if (bfs(list)) {
                ans++;
            }
            return ;
        }

        if (num < 0) return ;

        while (num >= 6 - depth) {
            num--;
            dfs(depth + 1, num, S, Y, new ArrayList<>(list));
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    // 조합으로 선택된 것들이 이어져 있는지 bfs로 확인
    private static boolean bfs(ArrayList<Integer> list) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        boolean[][] checkMap = new boolean[map.length][map[0].length];
        for (int a: list) checkMap[a / 5][a % 5] = true;
        int cnt = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {list.get(0) / 5, list.get(0) % 5});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cnt == 7)
                return true;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length) continue;
                if (visited[nx][ny]) continue;
                if (!checkMap[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                cnt++;
            }
        }
        return false;
    }
}
