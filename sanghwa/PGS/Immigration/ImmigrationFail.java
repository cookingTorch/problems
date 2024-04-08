package PGS.Immigration;

import java.util.Arrays;
import java.util.LinkedList;

public class ImmigrationFail {

    public static class Node {
        long endTime;
        int originTime;

        Node(int originTime) {
            this.originTime = originTime;
            this.endTime = originTime;
        }

        public void setEndTime() {
            endTime += originTime;
        }
    }
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        long result = solution(n, times);
        System.out.println(result);
    }

    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);
        LinkedList<Node> timeList = new LinkedList<>();

        for (int time : times)
            timeList.add(new Node(time));

        for (int i = 0; i < n - 1; i++) {
            Node target = timeList.getFirst();
            target.setEndTime();
            timeList.removeFirst();
            binaryInsertion(target, timeList);
        }

        return timeList.getFirst().endTime;
    }

    public static void binaryInsertion(Node target, LinkedList<Node> timeList) {
        int startIdx = 0;
        int endIdx = timeList.size() - 1;

        while (startIdx <= endIdx) {
            int midIdx = (startIdx + endIdx) / 2;
            long midValue = timeList.get(midIdx).endTime;

            if (midValue == target.endTime) {
                timeList.add(midIdx, target);
                return;
            } else if (target.endTime < midValue) {
                endIdx = midIdx - 1;
            } else
                startIdx = midIdx + 1;
        }
        if (endIdx < 0)
            timeList.addFirst(target);
        else if (startIdx >= timeList.size())
            timeList.add(target);
        else
            timeList.add(startIdx, target);
    }
}
