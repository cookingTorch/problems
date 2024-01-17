package AGS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Festival {

    public static void setCostArray(int[] cost, int dayNum, BufferedReader buffer) throws IOException {
        StringTokenizer stringToken = new StringTokenizer(buffer.readLine());
        for (int i = 0; i < dayNum; ++i)
            cost[i] = Integer.parseInt(stringToken.nextToken());
    }

    public static double getAverage(int[] cost, int start, int len) {
        double sum = 0;
        for (int i = start; i < start + len; ++i)
            sum += cost[i];
        return sum / (len);
    }

    public static double getMinCost(int[] cost, int dayNum, int teamNum) {
        double minAverage = 100.0;
        double average;
        int len = teamNum;

        while (len <= dayNum) {
            for (int i = 0; i + len <= dayNum; ++i) {
                average = getAverage(cost, i, len);
                minAverage = Math.min(minAverage, average);
            }
            len++;
        }
        return  minAverage;
    }

    public static void main(String[] args) throws IOException {
        int caseNum, dayNum, teamNum;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringToken = new StringTokenizer(buffer.readLine());

        caseNum = Integer.parseInt(stringToken.nextToken());

        for (int i = 0; i < caseNum; ++i) {
            stringToken = new StringTokenizer(buffer.readLine());
            dayNum = Integer.parseInt(stringToken.nextToken());
            teamNum = Integer.parseInt(stringToken.nextToken());
            int[] cost = new int[dayNum];
            setCostArray(cost, dayNum, buffer);
            double min = getMinCost(cost, dayNum, teamNum);
            System.out.printf("%.11f\n", min);
        }
    }
}