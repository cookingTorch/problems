import java.util.Scanner;

public class Main {
    static final int MAX = 1001;

    static double[][] mCache = new double[MAX][MAX];

    static double climb(int n, int m, int days, int climbed) {
        // m일이 지났을 때 n칸을 올라온 경우 1, 그렇지 않은 경우 0을 반환한다.
        if (days == m) return climbed >= n ? 1 : 0;
        double result = mCache[days][climbed];
        if (result != -1) return result;
        return 0.25 * climb(n, m, days + 1, climbed + 1) + 0.75 * climb(n, m, days + 1, climbed + 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();

        while (c-- > 0) {
            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    mCache[i][j] = -1;
                }
            }
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            System.out.printf("%.10f%n", climb(n, m, 0, 0));
        }
        scanner.close();
    }
}
