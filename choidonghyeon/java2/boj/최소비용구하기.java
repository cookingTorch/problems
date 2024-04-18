import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용구하기 {
	static class Node {
		int cityNum;
		int amount;

		public Node(int cityNum, int amount) {
			this.cityNum = cityNum;
			this.amount = amount;
		}
	}

	private static final int INF = (int)Math.pow(10, 11);

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, amount));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		System.out.println(dijkstra(graph, N, M, start, end));
	}

	private static int dijkstra(List<List<Node>> graph, int n, int m, int start, int end) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, INF);

		PriorityQueue<Node> q = new PriorityQueue<>((Comparator.comparingInt(node -> node.amount)));
		dp[start] = 0;
		q.add(new Node(start, 0));

		while (!q.isEmpty()) {
			Node curr = q.poll();

			if (curr.cityNum == end) {
				return dp[curr.cityNum];
			}

			if (dp[curr.cityNum] < curr.amount) {
				continue;
			}

			for (Node departure : graph.get(curr.cityNum)) {
				if (dp[departure.cityNum] > curr.amount + departure.amount) {
					dp[departure.cityNum] = curr.amount + departure.amount;
					q.add(new Node(departure.cityNum, curr.amount + departure.amount));
				}
			}
		}

		return dp[end];
	}
}
