import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 가장 가까운 공통 조상 / Gold 4 / 15m
 * - 168 ms
 * - 트리
 * - 첫 번째 노드에서 부모를 따라가면서 루트까지 방문 처리
 * - 두 번째 노드에서 부모를 따라가다 방문 처리된 노드를 만나면
 * -   해당 노드가 두 노드의 LCA
 * */
public class BOJ3584_가장가까운공통조상 {
    private static final char LINE_BREAK = '\n';

    private static BufferedReader br;

    private static int solution() throws IOException {
        int n;
        int v;
        int i;
        int[] parent;
        boolean[] visited;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        for (i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            parent[Integer.parseInt(st.nextToken())] = v; // 부모 입력
        }
        visited = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for (v = Integer.parseInt(st.nextToken()); v != 0; v = parent[v]) { // 부모를 따라가면서 루트까지
            visited[v] = true; // 방문 처리
        }
        for (v = Integer.parseInt(st.nextToken()); !visited[v]; v = parent[v]); // 부모를 따라가면서 방문 처리된 노드까지
        return v; // 방문 처리된 노드를 만나면 해당 노드가 LCA
    }

    public static void main(String[] args) throws IOException {
        int t;
        StringBuilder sb;

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb.toString());
    }
}
