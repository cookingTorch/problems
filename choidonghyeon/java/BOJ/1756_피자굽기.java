import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int[] minOvens = new int[d + 1];

		minOvens[0] = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i < d + 1; i++) {
			int length = Integer.parseInt(st.nextToken());
			minOvens[i] = Math.min(minOvens[i- 1], length);
		}

		st = new StringTokenizer(br.readLine());

		int currOven = d;

		for (int i = 0; i < n; i++) {
			int pizza = Integer.parseInt(st.nextToken());
			//여기서 시작.
			while (currOven > 0 && minOvens[currOven] < pizza) {
				currOven--;
			}
			currOven--;
			if (currOven == 0 && i != n - 1) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(currOven + 1);
	}
}
