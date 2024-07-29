package JavaCodingTestStudy.week6;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            points[i] = new Point(p, d);
        }

        Arrays.sort(points, (p1, p2) -> (p1.x == p2.x) ? p2.y - p1.y : p2.x -p1.x);
        //비용을 기준으로 내림차순 비용이 같으면 날짜를 기준으로 내림차순

        int sum = 0;
        boolean[] check = new boolean[10001];
        for (int i = 0; i < n; i++) {
            for (int j = points[i].y; j >= 1; j--) {
                if (!check[j]) {
                    check[j] = true;
                    sum += points[i].x;
                    break;
                }
            }
        }
        System.out.println(sum);


    }
}
