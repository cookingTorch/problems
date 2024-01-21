import java.util.Arrays;
import java.util.Scanner;

public class RockFestival {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(), l = scanner.nextInt();
            int[] cost = new int[n];

            for (int j = 0; j < n; j++) {
                cost[j] = scanner.nextInt();
            }
            System.out.println(String.format("%.11f", getMinAverageCost(n, l, cost)));
        }
        scanner.close();
    }

    private static double getMinAverageCost(int n, int l, int[] cost) {
        double minAverage = Double.MAX_VALUE;

        for (int i = 0; i <= n - l; i++) {
            for (int j = i + l; j <= n; j++) {
                int[] subArray = Arrays.copyOfRange(cost, i, j);
                double average = calculateAverage(subArray);
                minAverage = Math.min(minAverage, average);
            }
        }
        return minAverage;
    }

    private static double calculateAverage(int[] array) {
        int sum = 0;

        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }
}