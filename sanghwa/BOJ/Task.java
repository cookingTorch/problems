package BOJ;

import java.io.*;
import java.util.*;

public class Task {

    static class Node implements Comparable<Node>{
        int day;
        int score;

        public Node(int day, int score){
            this.day = day;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            return o.score - this.score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(buffer.readLine());
        StringTokenizer token;

        PriorityQueue<Node> que = new PriorityQueue<>();
        boolean[] check = new boolean[1001];

        for (int i = 0; i < N; i++){
            token = new StringTokenizer(buffer.readLine()," ");
            int day = Integer.parseInt(token.nextToken());
            int score = Integer.parseInt(token.nextToken());
            que.add(new Node(day, score));
        }

        int answer = 0;
        while(!que.isEmpty()){
            Node cur = que.poll();

            if(!check[cur.day]){
                answer += cur.score;
                check[cur.day] = true;
            } else {
                for (int j = cur.day - 1; j >= 1; j--){
                    if(!check[j]){
                        answer += cur.score;
                        check[j] = true;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}