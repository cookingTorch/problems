import java.io.*;
import java.util.*;

public class BOJ2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int len = n - k;

        Stack<Character> stack = new Stack<>();

        String num = br.readLine();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0) {
                if (stack.peek() < c) {
                    stack.pop();
                    k--;
                }
                else
                    break;
            }
            stack.push(c);
        }

        // 같은 숫자 여러개 일 경우 못지우므로 일단 다 받고 길이에 맞출때까지 pop 한다.
        while (stack.size() != len) {
            stack.pop();
        }

        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i));
        }
    }
}
