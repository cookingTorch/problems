package third.week2;

/**
 *
 * @author yongjunhong
 * @since 2024. 6. 13.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2036 {
	static long result;
	static int zeroCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		List<Long> positive = new ArrayList<>();
		List<Long> negative = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			long number = Long.parseLong(br.readLine());

			if (number == 1) {
				result += number;
			} else if (number == 0) {
				zeroCount++;
			} else if (number > 0) {
				positive.add(number);
			} else {
				negative.add(number);
			}
		}

		Collections.sort(positive, Collections.reverseOrder());
		Collections.sort(negative);

		calculateNumber(positive, true);
		calculateNumber(negative, false);

		System.out.println(result);
	}

	static void calculateNumber(List<Long> list, boolean isPositive) {
		int length = list.size();

		for (int i = 1; i < length; i += 2) {
			long first = list.get(i);
			long second = list.get(i - 1);

			result += (first * second);
		}

		if (length % 2 == 1 && (isPositive || zeroCount == 0)) {
			result += list.get(length - 1);
		}
	}
}

