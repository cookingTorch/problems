package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1080 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(token.nextToken());
        int y = Integer.parseInt(token.nextToken());
        int[][] a = new int[x][y];
        int[][] b = new int[x][y];

        for (int i = 0; i < x; i++) {
            a[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < x; i++) {
            b[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(getResult(a, b));
    }
    private static int getResult(int[][] a, int[][] b) {
        int count = 0;
        boolean flag = true;

        for (int i = 0; i <= a.length - 3; i++) {
            for (int j = 0; j <= a[i].length - 3; j++) {
                // A[r,c] != B[r,c]이면 count를 증가하고 A를 바꾼다.
                if (a[i][j] != b[i][j]) {
                    count++;
                    // a행렬을 바꾸는 메서드 호출
                    changeProcession(a, i, j);
                }

                // 행렬 AB가 같은 지 확인한다.
                if(equalsProceesion(a, b)) {
                    flag = false;
                    break;
                }
            }
        }

        // 다시 한번 행렬 a,b가 같은지 확인
        // 행렬이 3X3 이하일 경우
        if (!equalsProceesion(a, b)) return -1;

        return count;
    }

    private static void changeProcession(int[][] a, int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                a[i][j]++;
                a[i][j] = a[i][j] % 2;
            }
        }
    }

    private static boolean equalsProceesion(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
}
