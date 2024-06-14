import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 단어수학_1339 {
	/**
	 * 가장 긴 단어
	 * 의 첫글자가 높은수여야한다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		HashMap<Character, Long> alphabetCnt = new HashMap<>();
		while (N-- > 0) {
			String word = br.readLine();
			for (int i = 0; i < word.length(); i++) {
				if (!alphabetCnt.containsKey(word.charAt(i))) {
					alphabetCnt.put(word.charAt(i), 0L);
				}
				alphabetCnt.put(word.charAt(i), alphabetCnt.get(word.charAt(i)) + (long)Math.pow(10, word.length() - i - 1));
			}
		}

		PriorityQueue<Long> q = new PriorityQueue<>(alphabetCnt.values());

		int num = 10 - alphabetCnt.size();
		long answer = 0;
		while (!q.isEmpty()) {
			answer += q.poll() * num++;
		}

		System.out.println(answer);
	}
}
