import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] switches = new int[n + 1];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			switches[i + 1] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int switchNumber = Integer.parseInt(st.nextToken());

			if (gender == 1) { // 남성
				manipulateByMale(switches, switchNumber);
			} else {
				manipulateByFemale(switches, switchNumber);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < switches.length / 20 + 1 ; i++) {
			for (int j = i * 20 + 1; j < Math.min(i * 20 + 21, switches.length); j++) {
				sb.append(switches[j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	private static void manipulateByFemale(int[] switches, int switchNumber) {
		turnSwitch(switches, switchNumber);
		int left = switchNumber - 1;
		int right = switchNumber + 1;

		while (left >= 1 && right < switches.length) {
			if (switches[left] == switches[right]) {
				turnSwitch(switches, left);
				turnSwitch(switches, right);
			} else {
				break;
			}
			left--;
			right++;
		}
	}

	private static void manipulateByMale(int[] switches, int switchNumber) {
		for (int i = 1; i < switches.length; i++) {
			if (i * switchNumber >= switches.length) {
				break;
			}

			turnSwitch(switches, i * switchNumber);
		}
	}

	private static void turnSwitch(int[] switches, int switchNumber) {
		switches[switchNumber] = Math.abs(switches[switchNumber] - 1);
	}

}
