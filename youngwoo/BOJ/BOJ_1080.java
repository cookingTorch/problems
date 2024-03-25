import java.util.*;
import java.io.*;

public class BOJ_1080 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        char[][] firstArray = readArray(br, rows, columns);
        char[][] secondArray = readArray(br, rows, columns);

        if (!ChangesPossible(firstArray, secondArray, rows, columns)) {
            System.out.println(-1);
            return;
        }

        int changesNeeded = calculateChanges(firstArray, secondArray, rows, columns);
        System.out.println(changesNeeded);
    }

    //콘솔에서 배열을 읽는다
    private static char[][] readArray(BufferedReader br, int rows, int columns) throws IOException {
        char[][] array = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            String rowData = br.readLine();
            for (int j = 0; j < columns; j++) {
                array[i][j] = rowData.charAt(j);
            }
        }
        return array;
    }

    //두 배열을 동일하게 만들 수 있는지 검사
    //첫번째 배열을 두번째 배열로 변환이 가능한지 확인
    private static boolean ChangesPossible(char[][] arr1, char[][] arr2, int rows, int columns) {
        if (rows < 3 || columns < 3) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (arr1[i][j] != arr2[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return true;
    }

    //두 배열을 동일하게 만들기 위한 최소 변환 횟수 계산
    //첫번째 배열을 두번째 배열로 변환하기 위한 변환 횟수 계산
    private static int calculateChanges(char[][] arr1, char[][] arr2, int rows, int columns) {
        int changes = 0;

        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= columns - 3; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    flipSubmatrix(arr1, i, j);//필요하면 3x3 서브매트릭스를 뒤집는다
                    changes++;
                }
            }
        }
        //변환 성공 확인
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return -1; //변환 실패
                }
            }
        }

        return changes;
    }

    //3x3서브 행렬 값 모두 뒤집는다
    private static void flipSubmatrix(char[][] array, int startX, int startY) {
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                array[i][j] = array[i][j] == '0' ? '1' : '0';
            }
        }
    }
}