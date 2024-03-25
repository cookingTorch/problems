package second.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Procession {
    static boolean[][] array1;
    static boolean[][] array2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        array1 = new boolean[n][m];
        array2 = new boolean[n][m];

        // Array1 저장
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                array1[i][j] = checkWord(input.charAt(j));
            }
        }
        // Array2 저장
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                array2[i][j] = checkWord(input.charAt(j));
            }
        }

        // array1과 array2에 다른 부분이 있으면 3X3 크기의 원소 뒤집는다.
        int cnt = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                if (array1[i][j] != array2[i][j]) {
                    changeArray(i, j);
                    cnt++;
                }
            }
        }

        // 모든 원소를 탐색한 후에도 다른 부분이 있다면 A를 B로 바꿀 수 없다고 판단 함.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (array1[i][j] != array2[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(cnt);
    }

    static boolean checkWord(char number) {
        return number == '1';
    }

    static void changeArray(int y, int x) {
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                array1[i][j] = !array1[i][j];
            }
        }
    }
}
