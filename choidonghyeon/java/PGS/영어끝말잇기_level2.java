import java.util.HashSet;
import java.util.Set;

class Solution {
	public int[] solution(int n, String[] words) {
		Set<String> wordHistory = new HashSet<>();

		char targetTail = words[0].charAt(0);

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (targetTail != word.charAt(0) || wordHistory.contains(word)) {
				return new int[] {i % n + 1, i / n + 1};
			}
			targetTail = word.charAt(word.length() - 1);
			wordHistory.add(word);
		}

		return new int[] {0, 0};
	}
}
