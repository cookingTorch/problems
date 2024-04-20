package second.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1916 {
    static ArrayList<Node>[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        nodes = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes[start].add(new Node(end, cost));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end, n));
    }

    static int dijkstra(int start, int end, int n) {
        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        int result = 0;
        PriorityQueue<Node> nodequeue = new PriorityQueue<>();
        nodequeue.add(new Node(start, 0));
        while (!nodequeue.isEmpty()) {
            Node node = nodequeue.poll();
            if (node.index == end) {
                break;
            }
            if (check[node.index]) {
                continue;
            }
            check[node.index] = true;

            for (Node gNode : nodes[node.index]) {
                if (dist[gNode.index] > dist[node.index] + gNode.cost) {
                    dist[gNode.index] = dist[node.index] + gNode.cost;
                    nodequeue.add(new Node(gNode.index, dist[gNode.index]));
                }
            }
        }
        return dist[end];
    }
    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
