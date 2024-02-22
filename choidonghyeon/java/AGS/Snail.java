import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	static BigInteger[] factorials = new BigInteger[1001]; //factorials[n] = n! factorial 연산을 memorizing 처리
	static double[] sunnys = new double[1001];  //sunnys[n] = Math.pow(0.25,n) 제곱연산을 memorizing처리.
	static double[] rainys = new double[1001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		initializeFactorials();
		initializeRainys();
		initializeSunnys();

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			double possibility = 0;

			int maxSunnyDays = 0;

			if (n > 2 * m) { //우물의 깊이가 days의 두배가 넘는다면 모든 날에 비가와서 하루에 2m 씩 올라가도 우물의 끝에 도달하지 못함.
				print(0.0d);
				continue;
			} else if (n <= m) { //우물의 깊이가 days보다 작거나 같다면 모든 날에 비가 오거나 오지않더라도 무조건 우물의 끝에 도달함.
				print(1.0d);
				continue;
			} else {
				maxSunnyDays = 2 * m - n;  //최대 비가 오지 않을 수 있는 날의 수
			}

			for (int i = 0; i < maxSunnyDays + 1; i++) {
				possibility += calculateCombination(m, i) * sunnys[i] * rainys[m-i];  // nCr * 0.25의 i 제곱 * 0.75의 (i-m) 제곱
			}
			print(possibility);
		}
	}

	private static void print(double value) {
		System.out.printf("%.10f\n", value);
	}

	private static void initializeRainys() {
		rainys[0] = 1.0d;
		for (int i = 1; i < 1001; i++) {
			rainys[i] = rainys[i-1] * 0.75;
		}
	}

	private static void initializeSunnys() {
		sunnys[0] = 1.0d;
		for (int i = 1; i < 1001; i++) {
			sunnys[i] = sunnys[i-1] * 0.25;
		}
	}

	private static void initializeFactorials() {
		factorials[0] = BigInteger.ONE;
		factorials[1] = BigInteger.ONE;
		for (int i = 2; i < 1001; i++) {
			factorials[i] = factorials[i - 1].multiply(BigInteger.valueOf(i));
		}
	}

	/**
	 * 조합 연산
	 * calculateCombination(int n, int r) = nCr
	 * -> n!/ (n-r)! * r!
	 */
	private static double calculateCombination(int n, int r) {
		return factorials[n].divide(factorials[n - r].multiply(factorials[r])).doubleValue();
	}
}
