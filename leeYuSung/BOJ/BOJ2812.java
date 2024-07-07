package JavaCodingTestStudy.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B2812V2 {
    public static void main(String[] args) throws IOException {
        int N,K;
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        char[] chars = str.toCharArray();
        int popCount = 0;
        for (int i = 0; i < N; i++) {
            while(!stack.isEmpty() && stack.peek()<chars[i]-'0' && popCount<K) {
                //popCount가 K보다 작고 스택의 최상위에 있는 값이 다음 수보다 작다면 pop
                stack.pop();
                popCount++;
            }
            stack.push(chars[i]-'0');
        }

        while(popCount<K) {//남아있을 수도 있음
            stack.pop();
            popCount++;
        }
        for(int i = 0; i < stack.size(); i++) {//출력
            sb.append(stack.get(i));
        }
        System.out.println(sb);

    }
}
