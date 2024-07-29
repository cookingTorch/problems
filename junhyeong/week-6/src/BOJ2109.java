import java.util.*;
import java.io.*;

public class BOJ2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        ArrayList<Point> points = new ArrayList<Point>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            points.add(new Point(p, d));
        }

        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p2.p - p1.p;
            }
        });

        solve(points);
    }

    private static void solve(ArrayList<Point> points) {
        int[] ans = new int[10001];

        for (int i = 0; i < points.size(); i++) {
            int p = points.get(i).p;
            int d = points.get(i).d;
            for (int j = d; j >= 1; j--) {
                if (ans[j] < p) {
                    ans[j] = p;
                    break;
                }
            }
        }

        System.out.println(Arrays.stream(ans).sum());
    }

    static class Point {
        int p;
        int d;

        Point(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }
}