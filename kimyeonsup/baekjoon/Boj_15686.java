package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Math.*;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Boj_15686 {

    static boolean[] visited;
    static ArrayList<Position> home = new ArrayList<>();
    static ArrayList<Position> chicken = new ArrayList<>();
    public static int backTracking(int depth, int m) {
        int chickenSt = Integer.MAX_VALUE;
        int sum = 0;

        if (m == depth) {
            for (Position a : home) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < chicken.size(); i++) {
                    if(visited[i]) {
                        Position b = chicken.get(i);
                        min = min(min, abs(a.x - b.x) + abs(a.y - b.y));
                    }
                }
                sum += min;
            }
            return sum;
        }

        for (int i = depth; i <= chicken.size() + depth - m; i++) {
            if(!visited[i]) {
                visited[i] = true;
                chickenSt = min(chickenSt, backTracking(depth + 1, m));
                visited[i] = false;
            }
        }

        return chickenSt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        home = new ArrayList<>();
        chicken = new ArrayList<>();
        int homeCnt = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        for (int i = 0; i < homeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < homeCnt; j++) {
                String token = st.nextToken();
                if(token.equals("1")) {
                    home.add(new Position(i + 1, j + 1));
                } else if (token.equals("2")) {
                    chicken.add(new Position(i + 1, j + 1));
                }
            }
        }

        visited = new boolean[chicken.size()];



        int chickenSt = backTracking(0, m);

        System.out.println(chickenSt);
        br.close();
    }
}


/**
 5 3
 0 0 1 0 0
 0 0 2 0 1
 0 1 2 0 0
 0 0 1 0 0
 0 0 0 0 2


 5 2
 0 2 0 1 0
 1 0 1 0 0
 0 0 0 0 0
 2 0 0 1 1
 2 2 0 1 2

 5 1
 1 2 0 0 0
 1 2 0 0 0
 1 2 0 0 0
 1 2 0 0 0
 1 2 0 0 0

 5 1
 1 2 0 2 1
 1 2 0 2 1
 1 2 0 2 1
 1 2 0 2 1
 1 2 0 2 1
 */






