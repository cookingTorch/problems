import java.util.PriorityQueue;

public class MoreHot {
    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer>pq = new PriorityQueue<>();
        for(int i =0; i<scoville.length; i++){
            pq.add(scoville[i]);
        }
        int time = 0;
        while(pq.peek()<K){
            if(pq.size()>=2){
                int temp1 = pq.poll();
                int temp2 = pq.poll();
                pq.add(temp1 + temp2 * 2);
                time++;
            }else{
                return -1;
            }
        }
        return time;
    }
    public static void main(String[] args) {
        int [] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;
        System.out.println(solution(scoville,k));
    }
}
