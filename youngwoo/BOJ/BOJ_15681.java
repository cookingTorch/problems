import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15681 {
    static int N, R, Q;
    //N: 트리의 정점의 수, R:트리의 루트 정점 번호, Q:처리해야 하는 쿼리의 수
    static List<Integer>[] adj;
    //인접 리스트 방식으로 트리를 나타낸다. adj[i] = 정점 i와 연결된 정점 번호들의 리스트
    static int[] size;
    //size[i] = 정점 i를 루트로 하는 서브트리에 포함된 정점의 수를 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        //인접 리스트 초기화
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 두 노드를 서로의 인접 리스트에 추가
            adj[u].add(v);
            adj[v].add(u);
        }

        size = new int[N + 1];
        dfs(R, -1); //서브트리의 크기를 계산하기 위해 dfs 호출

        for (int i = 0; i < Q; i++) {
            int v = Integer.parseInt(br.readLine());
            System.out.println(size[v]);
        }
    }

    static void dfs(int cur, int parent) {
        size[cur] = 1;
        for (int next : adj[cur]) {
            if (next == parent) continue; //백트래킹을 피하기 위해 부모 노드를 건너뛴다
            dfs(next, cur); //자식 노드의 서브트리 크기를 계산하기 위해 재귀적으로 dfs를 호출
            size[cur] += size[next]; //계산된 자식 노드의 서브트리 크기를 현재 노드의 서브트리 크기에 추가
        }
    }
}
