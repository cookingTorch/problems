import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 수열의_점수_2036 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Long> yangs = new ArrayList<>();
		ArrayList<Long> ums = new ArrayList<>();
		boolean hasZero = false;

		for (int i = 0; i < N; i++) {
			long num = Long.parseLong(br.readLine());
			if (num > 0) {
				yangs.add(num);
			} else if (num < 0) {
				ums.add(num);
			} else {
				hasZero = true;
			}
		}

		long answer = 0;
		Collections.sort(yangs);
		ums.sort(Collections.reverseOrder());

		while (yangs.size() >= 2) {
			long num1 = yangs.remove(yangs.size() - 1);
			long num2 = yangs.remove(yangs.size() - 1);

			if (num1 == 1 || num2 == 1) {
				yangs.add(num1);
				yangs.add(num2);
				break;
			}
			answer += num1 * num2;
		}

		while (ums.size() >= 2) {
			answer += ums.remove(ums.size() - 1) *  ums.remove(ums.size() - 1);
		}

		while (!yangs.isEmpty()) {
			answer += yangs.remove(0);
		}

		if (!ums.isEmpty() && !hasZero) {
			answer += ums.remove(0);
		}

		System.out.println(answer);
	}
}
