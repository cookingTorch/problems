import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;

public class 암호만들기 {
	static Set<String> moums = Set.of("a", "e", "i", "o", "u");
	static String[] cands;
	static String[] curr;
	static StringBuilder ans = new StringBuilder();
	static int L, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		cands = new String[C];
		curr = new String[L];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < C; i++) {
			cands[i] = st.nextToken();
		}

		Arrays.sort(cands);
		backtracking(0, 0);
		System.out.println(ans);
	}

	private static void backtracking(int candIdx,int currIdx) {
		if (currIdx == L) {
			if (valid()) {
				ans.append(String.join("",curr)).append("\n");
			}
			return;
		}

		if (candIdx >= cands.length) {
			return;
		}

		curr[currIdx] = cands[candIdx];
		backtracking(candIdx + 1, currIdx + 1);
		backtracking(candIdx + 1, currIdx);
	}

	private static boolean valid() {
		int moumCnt = 0;
		int jaumCant = 0;
		for(String cand : curr) {
			if (moums.contains(cand)) {
				moumCnt++;
			} else {
				jaumCant++;
			}
		}
		return moumCnt >= 1 && jaumCant >= 2;
	}
}
