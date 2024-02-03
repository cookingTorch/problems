import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 스위핑, 스택을 활용한 풀이
 * 시간복잡도: O(2N) 추정
 * 실행속도: 280ms
 */

public class Fence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] fences = new int[N+1];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                fences[i] = Integer.parseInt(st.nextToken());
            }
            fences[N] = 0; //마지막 판자에 도달했을때 동작하게 의미없는 숫자를 추가.

            Deque<Integer> stack = new ArrayDeque<>();

            int result = 0;
            int length;

            for (int i = 0; i < N + 1; i++) {
                while (!stack.isEmpty() && fences[stack.peek()] >= fences[i]) { //stack이 비어있지않고 stack의 최근 높이보다 현재 높이가 크지 않다면 -->새로 갱신
                    int lastPosition = stack.pop(); //stack의 최근 높이를 제거.

                    if (stack.isEmpty()) { //스택이비어있다면
                        length = i;
                    } else { //스택이 비어있지않다면
                        length = i - stack.peek() - 1;
                    }

                    int area = length * fences[lastPosition];
                    result = Math.max(result,area);
                }
                stack.push(i);
            }
            System.out.println(result);
        }
    }
}
