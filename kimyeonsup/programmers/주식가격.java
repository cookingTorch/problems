package pgs;

public class 주식가격 {
    public int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int[length];
        for (int i = 0; i < length - 1; i++) {
            int count = 1;
            int price = prices[i];
            for (int j = i + 1; j < length - 1; j++) {
                if (price > prices[j]) break;

                count++;
            }
            answer[i] = count;
        }
        return answer;
    }
}
