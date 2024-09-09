import java.util.PriorityQueue;

public class 더맵게 {
	public int solution(int[] scoville, int K) {
		int answer = 0;

		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int sco : scoville) {
			q.add(sco);
		}

		while (q.size() >= 2 && q.peek() < K) {
			q.add(q.poll() + (q.poll() * 2));
			answer++;
		}

		if (q.peek() < K) {
			return -1;
		}

		return answer;
	}
}

