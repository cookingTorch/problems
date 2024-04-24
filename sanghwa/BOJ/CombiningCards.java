package BOJ;
//15903

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CombiningCards {

    static int cardNum;
    static int combCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());

        cardNum = Integer.parseInt(token.nextToken());
        combCnt = Integer.parseInt(token.nextToken());


        token = new StringTokenizer(buffer.readLine());
        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (int i = 0; i < cardNum; i++) {
            long value = Long.parseLong(token.nextToken());
            queue.offer(value);
        }

        for (int i = 0; i < combCnt; i++) {
            Long value1 = queue.poll();
            Long value2 = queue.poll();
            long newValue = value1 + value2;
            queue.offer(newValue);
            queue.offer(newValue);
        }

        long result = 0L;
        for (int i = 0; i < cardNum; i++) {
            Long value = queue.poll();
            result += value;
        }
        System.out.print(result);
    }
}
