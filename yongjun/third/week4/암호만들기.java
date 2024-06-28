import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 암호만들기 {
	private static String[] words;
	private static ArrayList<String> result = new ArrayList<>();
	private static int l, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		words = new String[c];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			words[i] = st.nextToken();
		}
		Arrays.sort(words);

		for (int i = 0; i < c - 3; i++) {
			backTracking(1, i, new boolean[c], words[i]);
		}
		Collections.sort(result);
		result.forEach(System.out::println);
	}

	private static void backTracking(int depth, int idx, boolean[] visited, String res) {
		if (depth == l) {
			if (hasVowelsAndTwoConsonant(res)) {
				result.add(res);
			}
			return;
		}

		for (int i = idx + 1; i < c; i++) {
			if (!visited[i]) {
				String nWord = res.concat(words[i]);
				visited[i] = true;
				backTracking(depth + 1, i, visited, nWord);
				visited[i] = false;
			}
		}
	}

	static boolean hasVowelsAndTwoConsonant(String input) {
		int length = input.length();
		int vowels = 0;
		int consonant = 0;
		for (int i = 0; i < length; i++) {
			char inp = input.charAt(i);
			if (inp == 'a' || inp == 'e' || inp == 'i' || inp == 'o' || inp == 'u') {
				vowels++;
			} else {
				consonant++;
			}
		}
		return vowels >= 1 && consonant >= 2;
	}

}