import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			int answer = 0;
			int[] numbers = new int[N + 1];
			char[] chars = br.readLine().toCharArray();
			for (int i = 1; i < N + 1; i++) {
				numbers[i] = chars[i - 1] - '0';
			}

			char[] mines = new char[N + 2];
			mines[0] = mines[N + 1] = 'X';

			String line = br.readLine();
			for (int i = 1; i < N + 1; i++) {
				mines[i] = line.charAt(i - 1);
				if (mines[i] == '*') {
					answer++;
				}
			}

			for (int i = 1; i < N + 1; i++) {
				int target = numbers[i];
				int temp = 0;

				for (int j = -1; j < 2; j++) {
					if (mines[i + j] == 'X') {
						continue;
					}

					if (mines[i + j] == '*') {
						temp++;
						continue;
					}

					if (target > temp) {
						mines[i + j] = '*';
						temp++;
						answer++;
						continue;
					}

					mines[i + j] = 'X';
				}
			}
			System.out.println(answer);
		}
	}
}
