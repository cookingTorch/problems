/*
* https://school.programmers.co.kr/learn/courses/30/lessons/42584?language=java#
* 주식 가격 level.2
* */
class PGS_StockPrice {
    public static int[] solution(int[] prices) {
        int sec = 0;
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int cnt = 0;
            for (int j = i + 1; j < prices.length; j++) {
                cnt++;
                if (prices[i] > prices[j])
                    break;
            }
            answer[i] = cnt;
        }
        return answer;
    }

    public static void main(String[] args) {
        solution(new int[]{1, 2, 3, 2, 3});
    }
}
