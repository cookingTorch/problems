package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3584 {
	static int[] tree;
	static int[] target;
	static StringTokenizer token;
	static BufferedReader buffer;

	public static void main(String[] args) throws IOException {
		buffer = new BufferedReader(new InputStreamReader(System.in));
		token = new StringTokenizer(buffer.readLine());

		int testNum = Integer.parseInt(token.nextToken());
		target = new int[2];

		while (testNum-- > 0) {

			int nodeNum = Integer.parseInt(buffer.readLine());
			makeTree(nodeNum);
			System.out.println(solve());
		}
	}

	static int solve() {
		boolean[] visited = new boolean[tree.length];
		int parent = target[0];
		while (parent > 0) {
			visited[parent] = true;
			parent = tree[parent];
		}
		parent = target[1];
		while (parent > 0) {
			if (visited[parent]) {
				return parent;
			}
			parent = tree[parent];
		}
		return parent;
	}

	static void makeTree(int nodeNum) throws IOException {
		tree = new int[nodeNum + 1];
		
		for (int i = 0; i < nodeNum - 1; i++) {
			token = new StringTokenizer(buffer.readLine());
			int parent = Integer.parseInt(token.nextToken());
			int child = Integer.parseInt(token.nextToken());

			tree[child] = parent;
		}
		token = new StringTokenizer(buffer.readLine());
		target[0] = Integer.parseInt(token.nextToken());
		target[1] = Integer.parseInt(token.nextToken());
	}
}
