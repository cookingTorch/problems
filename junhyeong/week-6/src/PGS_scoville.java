import java.util.*;

class PGS_scoville {
    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int j : scoville) {
            pq.offer(j);
        }

        while(!pq.isEmpty()) {
            int min1 = pq.poll();

            if (min1 >= K)
                return answer;
            if (pq.isEmpty())
                break;

            int min2 = pq.poll();
            int newScoville = min1 + (min2 * 2);

            pq.offer(newScoville);
            answer++;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1}, 7));
    }
}