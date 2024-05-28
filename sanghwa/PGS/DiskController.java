package PGS;

import java.util.*;

class DiskController {

    public static class Node {
        public int reqTime;
        public int taskTime;

        public Node(int reqTime, int taskTime) {
            this.reqTime = reqTime;
            this.taskTime = taskTime;
        }
    }

    public static void main(String[] args) {
        int[][] data = new int[][]{{0, 3}, {4, 4}, {5, 3}, {7, 1}};
        int result = solution(data);
        if (result != 4) throw new AssertionError();
        System.out.println("result = " + result);
    }

    public static int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<Node> reqQue = new PriorityQueue<>(Comparator.comparingInt(node -> (node.reqTime)));
        for (int[] ints : jobs) {
            reqQue.offer(new Node(ints[0], ints[1]));
        }
        int currTime = reqQue.peek().reqTime;
        PriorityQueue<Node> taskQue = new PriorityQueue<>(Comparator.comparingInt(node -> node.taskTime));
        while (!reqQue.isEmpty() || !taskQue.isEmpty()) {
            while (!reqQue.isEmpty() && reqQue.peek().reqTime <= currTime) {
                Node node = reqQue.poll();
                taskQue.offer(node);
            }
            if (taskQue.isEmpty()) {
                currTime = reqQue.peek().reqTime;
            } else {
                Node node = taskQue.poll();
                answer += currTime + node.taskTime - node.reqTime;
                currTime += node.taskTime;
            }
        }

        answer = answer / jobs.length;

        return answer;
    }
}