import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();

        while (c-- > 0) {
            System.out.println(solution(scanner.nextInt()));
        }
        scanner.close();
    }

    public static int solution(int n) {
        return Stream.iterate(new AbstractMap.SimpleEntry<>(1, 2), fib -> new AbstractMap.SimpleEntry<>(fib.getValue(), (fib.getKey() + fib.getValue()) % 1_000_000_007))
                .limit(n)
                .reduce((acc, i) -> i)
                .orElseThrow()
                .getKey();
    }
}
