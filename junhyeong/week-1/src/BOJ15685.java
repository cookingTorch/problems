import java.util.*;
import java.io.*;

public class BOJ15685 {
//    public static void change(int[][] arr, int x, int y, int d) {
//        if (d == 0)
//            arr[++x][y] = 1;
//        if (d == 1)
//            arr[x][--y] = 1;
//        if (d == 2)
//            arr[--x][y] = 1;
//        if (d == 3)
//            arr[x][++y] = 1;
//    }

    public static void Dragon(int[][] arr, int x, int y, int d, int g) {
        ArrayList<Integer> history = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        arr[y][x] = 1;
        tmp.add(d);
        while (g-- >= 0) {
            ArrayList<Integer> list = new ArrayList<>(tmp);
            tmp.clear();
            for (int i = 0; i < list.size(); i++) {
                int t = list.get(i);
                if (t == 0) {
                    arr[y][++x] = 1;
                }
                if (t == 1) {
                    arr[--y][x] = 1;
                }
                if (t == 2) {
                    arr[y][--x] = 1;
                }
                if (t == 3) {
                    arr[++y][x] = 1;
                }
                history.add(t);
            }
            for (int i = history.size() - 1; i >= 0; i--) {
                int t = history.get(i);
                tmp.add((t + 1) % 4);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] arr = new int[101][101];

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            Dragon(arr, x, y, d, g);
        }
        int cnt = CountSq(arr);
        System.out.println(cnt);
    }

    private static int CountSq(int[][] arr) {
        int cnt = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[i].length - 1; j++) {
                if (arr[i][j] != 1) continue;
                if (arr[i][j+1] != 1) continue;
                if (arr[i+1][j] != 1) continue;
                if (arr[i+1][j+1] != 1) continue;
                cnt++;
            }
        }
        return cnt;
    }
}
