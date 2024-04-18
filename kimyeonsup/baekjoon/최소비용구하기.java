package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최소비용구하기 {
    static int n, m;
    static ArrayList<Node>[] list;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());

        list = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer token = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());
            int weight = Integer.parseInt(token.nextToken());

            list[start].add(new Node(end, weight));
        }

        StringTokenizer token = new StringTokenizer(bf.readLine());

        int start = Integer.parseInt(token.nextToken());
        int end = Integer.parseInt(token.nextToken());

        djistra(start);

        System.out.println(dist[end]);
    }

    static void djistra(int start) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.end] = true;

            if (node.weight > dist[node.end]) {
                continue;
            }

            for (Node target : list[node.end]) {
                if (visited[target.end]) {
                    continue;
                }

                if (dist[target.end] > dist[node.end] + target.weight) {
                    dist[target.end] = dist[node.end] + target.weight;
                    queue.add(new Node(target.end, dist[target.end]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
