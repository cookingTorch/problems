import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [BOJ] 이차원 배열과 연산 / Gold 4 / 100m
 * - 88 ms
 * - 구현
 * - 숫자별 등장 횟수 class Count (num, cnt)
 * - 배열 순회하면서 0이 아니면 해당 숫자의 cnt++
 * - 처음 방문하는 숫자인 경우 Count 객체 temp 배열에 삽입
 * - temp 정렬
 * - temp에서 Count 하나씩, 최대 50개 꺼내면서
 * - 배열 A에 num, cnt 삽입, cnt와 방문여부 초기화
 * - 나머지 공간 0으로 초기화
 * - 숫자가 들어있는 길이 = temp 크기 * 2
 * - 열의 개수 = 행 최대 길이, 행의 개수 = 열 최대 길이
 * */
public class BOJ17140_이차원배열과연산 {
    private static final int INIT = 3;
    private static final int SIZE = 100;
    private static final int HALF = SIZE >> 1;
    private static final int[] EMPTY = new int[SIZE];
    private static final char[] FAIL = {'-', '1'};

    private static final class Count implements Comparable<Count> {
        int num;
        int cnt;

        Count(int num) {
            this.num = num;
            this.cnt = 0;
        }

        @Override
        public int compareTo(Count o) {
            if (this.cnt == o.cnt) { // 등장 횟수가 같으면
                return this.num - o.num; // 수가 커지는 순
            } else {
                return this.cnt - o.cnt; // 등장 횟수가 커지는 순
            }
        }
    }

    private static int[][] arr;
    private static boolean[] visited;
    private static Count[] counts;
    private static Count[] temp;

    private static int rowUpdate(int[] arr) {
        int i;
        int size;

        size = 0;
        for (int num : arr) { // 배열 순회
            if (num != 0) { // 0이 아니면
                if (!visited[num]) { // 처음 방문하는 숫자인 경우
                    visited[num] = true; // 방문 처리
                    temp[size++] = counts[num]; // temp 배열에 Count 객체 삽입
                }
                counts[num].cnt++; // 해당 숫자의 cnt++
            }
        }
        Arrays.sort(temp, 0, size); // temp 정렬
        size = Math.min(size, HALF); // 최대 50개
        for (i = 0; i < size; i++) { // temp에서 Count 하나씩 꺼내면서
            arr[i << 1] = temp[i].num; // 배열 A에 num 삽입
            arr[(i << 1) + 1] = temp[i].cnt; // 배열 A에 cnt 삽입
            visited[temp[i].num] = false; // 방문 여부 초기화
            temp[i].cnt = 0; // cnt 초기화
        }
        if ((i <<= 1) < SIZE) { // 나머지 공간 0으로 초기화
            System.arraycopy(EMPTY, 0, arr, i, SIZE - i);
        }
        return size << 1; // 숫자가 들어있는 길이 = temp 크기 * 2
    }

    private static int colUpdate(int idx) {
        int i;
        int num;
        int size;

        size = 0;
        for (i = 0; i < SIZE; i++) { // 배열 순회
            if ((num = arr[i][idx]) != 0) { // 0이 아니면
                if (!visited[num]) { // 처음 방문하는 숫자인 경우
                    visited[num] = true; // 방문 처리
                    temp[size++] = counts[num]; // temp 배열에 Count 객체 삽입
                }
                counts[num].cnt++; // 해당 숫자의 cnt++
            }
        }
        Arrays.sort(temp, 0, size); // temp 정렬
        size = Math.min(size, HALF); // 최대 50개
        for (i = 0; i < size; i++) { // temp에서 Count 하나씩 꺼내면서
            arr[i << 1][idx] = temp[i].num; // 배열 A에 num 삽입
            arr[(i << 1) + 1][idx] = temp[i].cnt; // 배열 A에 cnt 삽입
            visited[temp[i].num] = false; // 방문 여부 초기화
            temp[i].cnt = 0; // cnt 초기화
        }
        for (i <<= 1; i < SIZE; i++) {
            arr[i][idx] = 0; // 나머지 공간 0으로 초기화
        }
        return size << 1; // 숫자가 들어있는 길이 = temp 크기 * 2
    }

    public static void main(String[] args) throws IOException {
        int r;
        int c;
        int k;
        int i;
        int j;
        int time;
        int rowSize;
        int colSize;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        arr = new int[SIZE][SIZE];
        for (i = 0; i < INIT; i++) {
            st = new StringTokenizer(br.readLine());
            for (j = 0; j < INIT; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()); // 초기 배열
            }
        }
        visited = new boolean[SIZE + 1];
        counts = new Count[SIZE + 1];
        for (i = 1; i <= SIZE; i++) { // 숫자별 Count 객체
            counts[i] = new Count(i);
        }
        temp = new Count[SIZE];
        rowSize = INIT; // 열의 개수 (행 최대 길이)
        colSize = INIT; // 행의 개수 (열 최대 길이)
        for (time = 0; arr[r][c] != k && time < SIZE; time++) { // 100초까지 시간 측정
            if (colSize >= rowSize) { // 행의 개수 >= 열의 개수
                rowSize = 0; // 열의 개수 (행 최대 길이) 재측정
                for (i = 0; i < colSize; i++) { // 모든 행에 대해
                    rowSize = Math.max(rowSize, rowUpdate(arr[i])); // 행 업데이트
                }
            } else { // 열의 개수 > 행의 개수
                colSize = 0; // 행의 개수 (열 최대 길이) 재측정
                for (i = 0; i < rowSize; i++) { // 모든 열에 대해
                    colSize = Math.max(colSize, colUpdate(i)); // 열 업데이트
                }
            }
        }
        if (arr[r][c] == k) { // 100초 안에 k 도달
            System.out.print(time); // 시간 출력
        } else {
            System.out.print(FAIL);
        }
    }
}
