import java.util.*;

public class Fence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[] heights = new int[n];

            for (int j = 0; j < n; j++) {
                heights[j] = sc.nextInt();
            }
            System.out.println(findLargestRectangle(n, heights));
        }
    }

    private static int findLargestRectangle(int n, int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxRectangle = 0;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxRectangle = Math.max(maxRectangle, height * width);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxRectangle = Math.max(maxRectangle, height * width);
        }
        return maxRectangle;
    }
}
