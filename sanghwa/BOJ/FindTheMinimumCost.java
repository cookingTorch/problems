package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FindTheMinimumCost {

    public static class Node implements Comparable<Node> {
        int city, cost;

        public Node(int now, int cost) {
            this.city = now;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int[] dis;
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static int cityNum;
    static int busNum;
    static int startCity;
    static int endCity;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());

        cityNum = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(buffer.readLine());
        busNum = Integer.parseInt(token.nextToken());

        for (int i = 0; i <= cityNum; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < busNum; i++) {
            token = new StringTokenizer(buffer.readLine());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());
            int price = Integer.parseInt(token.nextToken());

            list.get(start).add(new Node(end, price));
        }

        token = new StringTokenizer(buffer.readLine());
        startCity = Integer.parseInt(token.nextToken());
        endCity = Integer.parseInt(token.nextToken());

        dis = new int[cityNum + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        bfs();
    }

    public static void bfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startCity, 0));
        dis[startCity] = 0;

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            int currCity = temp.city;
            int currCost = temp.cost;

            if (currCost > dis[currCity])
                continue;

            for (Node next : list.get(currCity)) {
                int newCost = next.cost + currCost;
                if (dis[next.city] > newCost) {
                    dis[next.city] = newCost;
                    queue.add(new Node(next.city, newCost));
                }
            }
        }
        System.out.println(dis[endCity]);
    }
}
