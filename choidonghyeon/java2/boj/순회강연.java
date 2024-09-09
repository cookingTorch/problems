import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 순회강연 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> requests = new PriorityQueue<>(Comparator.comparingInt(request -> -request[0]));

		int[] assignment = new int[10001];
		for (int i = 0; i < 10001; i++) {
			assignment[i] = i;
		}

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			requests.add(new int[] {p, d});
		}

		int answer = 0;
		while (!requests.isEmpty()) {
			int[] request = requests.poll();
			int p = request[0];
			int d = request[1];

			if (updateRoot(assignment,d) != -1) {
				answer += p;
			}
		}
		System.out.println(answer);
	}

	private static int updateRoot(int[] assignment, int d) {
		if (assignment[d] == 0) {
			return -1;
		}

		if (assignment[d] == d) {
			assignment[d] = d-1;
			return d-1;
		} else {
			int result = updateRoot(assignment, assignment[d]);
			if (result != -1) {
				return assignment[d] = result;
			}
			return -1;
		}
	}
}
