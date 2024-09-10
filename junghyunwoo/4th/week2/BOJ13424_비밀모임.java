import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 비밀 모임 / Gold 4 / 22m
 * - 200 ms
 * - 플로이드-워셜
 * - 모든 노드 간 최단 거리 생성 (플로이드-워셜)
 * - 각 노드들로부터 친구가 존재하는 노드들까지의
 * -   거리합이 최소인 노드
 * */
public class BOJ13424_비밀모임 {
    private static final int MAX_N = 100;
    private static final int INF = Integer.MAX_VALUE >> 1;
    private static final char LINE_BREAK = '\n';

    private static int[] infs;
    private static int[] friends;
    private static int[][] dist;
    private static BufferedReader br;

    private static int solution() throws IOException {
        int n;
        int m;
        int u;
        int v;
        int k;
        int weight;
        int sum;
        int min;
        int ans;
        int i;
        int j;
        int[] distI;
        int[] distU;
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (i = 1; i <= n; i++) { // dist 배열 INF 초기화, dist[i][i] = 0
            System.arraycopy(infs, MAX_N - i, dist[i], 1, n);
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            dist[u][v] = weight; // 간선
            dist[v][u] = weight;
        }
        for (i = 1; i <= n; i++) { // 플로이드-워셜
            distI = dist[i];
            for (u = 1; u <= n; u++) {
                distU = dist[u];
                for (v = 1; v <= n; v++) {
                    distU[v] = Math.min(distU[v], distU[i] + distI[v]);
                } // 저장된 (u, v) 최단 거리와 정점 i를 거쳐가는 (u, i) + (i, v) 비교하여 업데이트
            }
        }
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < k; i++) { // 친구들 위치
            friends[i] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
        min = INF;
        for (i = 1; i <= n; i++) {
            distI = dist[i];
            sum = 0;
            for (j = 0; j < k; j++) {
                sum += distI[friends[j]]; // 친구가 존재하는 노드들까지의 거리합
            }
            if (sum < min) {
                min = sum;
                ans = i; // 거리합이 최소인 노드
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int t;
        int i;
        StringBuilder sb;

        infs = new int[(MAX_N << 1) - 1];
        for (i = MAX_N - 2; i >= 0; i--) {
            infs[i] = INF;
        }
        System.arraycopy(infs, 0, infs, MAX_N, MAX_N - 1);
        dist = new int[MAX_N + 1][MAX_N + 1];
        friends = new int[MAX_N];
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
