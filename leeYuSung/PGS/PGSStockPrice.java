package JavaCodingTestStudy.week5;

public class P_StockPrice {
    public static int[] solution(int[] prices) {
        int [] result = new int [prices.length];
        for(int i=0; i<prices.length-1; i++) {
            int j = i+1;
            int time = 0;
            while(j < prices.length) {
                if(prices[i] <= prices[j]) {
                    time++;
                    j++;
                }else{
                    time++;
                    break;
                }
            }
            result[i] = time;
        }
        return result;
    }
    public static void main(String[] args) {
        int [] result = solution(new int[] {1, 2, 3, 2, 3});
        for(int i=0; i<result.length; i++) {
            System.out.print(result[i]+" ");
        }
    }
}
