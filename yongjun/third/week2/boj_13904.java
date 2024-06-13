package third.week2;

/**
 *
 * @author yongjunhong
 * @since 2024. 6. 13.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_13904 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int testCase = Integer.parseInt(br.readLine());

		ArrayList<Number> arrayList = new ArrayList<>();
		for (int i = 0; i < testCase; i++) {
			st = new StringTokenizer(br.readLine());

			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			arrayList.add(new Number(d, w));
		}
		Collections.sort(arrayList);

		int[] resultArray = new int[1001];
		int length = arrayList.size();
		for (int j = 0; j < length; j++) {
			Number number = arrayList.get(0);
			for (int i = number.deadline; i > 0; i--) {
				if (resultArray[i] == 0) {
					resultArray[i] = number.value;
					break;
				}
			}
			arrayList.remove(number);
		}
		int result = 0;
		for (int i : resultArray) {
			result += i;
		}
		System.out.println(result);
	}

	static class Number implements Comparable<Number> {
		int deadline;
		int value;

		public Number(int deadline, int value) {
			this.deadline = deadline;
			this.value = value;
		}

		@Override
		public int compareTo(Number o) {
			if (this.value == o.value) {
				return this.deadline - o.deadline;
			}
			return o.value - this.value;
		}
	}
}
