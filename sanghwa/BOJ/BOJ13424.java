package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13424 {

	static class Node implements Comparable<Node> {
		int toIdx;
		int cost;

		Node(int toIdx, int cost) {
			this.toIdx = toIdx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static ArrayList<Node>[] list;
	static int[] dist;
	static int roomNum;
	static int maxValue = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(buffer.readLine());

		int testNum = Integer.parseInt(token.nextToken());

		while (testNum-- > 0) {
			token = new StringTokenizer(buffer.readLine());
			roomNum = Integer.parseInt(token.nextToken());
			int pathNum = Integer.parseInt(token.nextToken());
			list = new ArrayList[roomNum + 1];
			for (int i = 0; i < roomNum + 1; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < pathNum; i++) {
				token = new StringTokenizer(buffer.readLine());
				int room1 = Integer.parseInt(token.nextToken());
				int room2 = Integer.parseInt(token.nextToken());
				int cost = Integer.parseInt(token.nextToken());
				list[room1].add(new Node(room2, cost));
				list[room2].add(new Node(room1, cost));
			}

			int K = Integer.parseInt(buffer.readLine());
			token = new StringTokenizer(buffer.readLine());
			int[] ans = new int[roomNum + 1];
			for (int i = 0; i < K; i++) {
				int target = Integer.parseInt(token.nextToken());
				dijkstra(target);
				for (int j = 1; j <= roomNum; j++) {
					ans[j] += dist[j];
				}
			}
			int result = maxValue;
			int resultIdx = 0;
			for (int i = 1; i <= roomNum; i++) {
				if (ans[i] < result) {
					result = ans[i];
					resultIdx = i;
				}
			}
			System.out.println(resultIdx);
		}
	}

	static void dijkstra(int start) {
		dist = new int[roomNum + 1];
		boolean[] visited = new boolean[roomNum + 1];
		Arrays.fill(dist, maxValue);
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.add(new Node(start, 0));
		dist[start] = 0;

		while (!que.isEmpty()) {
			Node curr = que.poll();
			int to = curr.toIdx;

			if (visited[to]) {
				continue;
			}

			visited[to] = true;

			for (Node node : list[to]) {
				if (dist[node.toIdx] > dist[to] + node.cost) {
					dist[node.toIdx] = dist[to] + node.cost;
					que.add(new Node(node.toIdx, dist[node.toIdx]));
				}
			}
		}
	}
}
