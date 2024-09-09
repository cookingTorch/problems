import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장_가까운_조상_3584 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-->0) {
			int N = Integer.parseInt(br.readLine());
			boolean[] visited = new boolean[N+1];
			int[] tree = new int[N+1];

			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				tree[child] = parent;
			}

			int root = findRoot(tree);

			st= new StringTokenizer(br.readLine());
			int targetA = Integer.parseInt(st.nextToken());
			int targetB = Integer.parseInt(st.nextToken());

			int curr = targetA;
			while (curr != root) {
				visited[curr] = true;
				curr = tree[curr];
			}
			visited[root] = true;

			curr = targetB;
			while (!visited[curr]) {
				curr = tree[curr];
			}
			System.out.println(curr);
		}
	}

	private static int findRoot(int[] tree) {
		for (int i = 1; i < tree.length; i++) {
			if (tree[i] == 0) {
				return i;
			}
		}
		return -1;
	}
}
