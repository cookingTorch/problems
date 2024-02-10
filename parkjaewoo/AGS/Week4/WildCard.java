package AGS.Week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.parseInt;

// PriorityQueue 조심 -> sort된 값이 아닌 Tree형태로 저장되다보니 순서가 정렬된 상태가 아님

public class WildCard {
    static int[][] check;
    static String wildName;
    static String fileName;
    static int wildLength;
    static int fileLength;
    private static void checkInit() {
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                check[i][j] = -1; // 아직 방문하지 않았다는 표시 : -1
            }
        }
    }

    private static int recursive(int wIdx, int fIdx) {
        if (check[wIdx][fIdx] != -1) { // 만약 체크된 적이 있다면 check[wIdx][fIdx] 반환 (동적 계획법의 원리)
            return check[wIdx][fIdx];
        }

        if (wIdx < wildLength && fIdx < fileLength) { // '?'이거나, 두 index의 값이 같다면 재귀 호출 (index값 1씩 더해서)
            if (wildName.charAt(wIdx) == fileName.charAt(fIdx) || wildName.charAt(wIdx) == '?') {
                return check[wIdx][fIdx] = recursive(wIdx + 1, fIdx + 1);
            }
        }

        if (wIdx == wildLength) {
            if (fIdx == fileLength) {
                return check[wIdx][fIdx] = 1; // 만약 다 충족 되었다면 : 1 저장
            } else {
                return check[wIdx][fIdx] = 0; // 만약 다 충족 되지 못했다면 : 0 저장
            }
        }

        if (wildName.charAt(wIdx) == '*') { // '*'일 때
            if (recursive(wIdx + 1, fIdx) == 1 || (fIdx < fileLength && recursive(wIdx, fIdx + 1) == 1)) {
                return check[wIdx][fIdx] = 1; // 조합을 사용하여 wIdx + 1일 때와 fIdx + 1일 때를 통해 재귀함수 호출
            }
        }

        return check[wIdx][fIdx] = 0; // 모든 조건을 충족하지 못한다면 0 반환
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = parseInt(br.readLine());

        while (c-- > 0) {
            ArrayList<String> list = new ArrayList<>();
            wildName = br.readLine();
            int n = parseInt(br.readLine());
            check = new int[101][101];
            for (int i = 0; i < n; i++) {
                fileName = br.readLine();
                checkInit();
                wildLength = wildName.length();
                fileLength = fileName.length();
                if (recursive(0, 0) == 1) {
                    list.add(fileName);
                }
            }
            Collections.sort(list);
            for (String answer : list) {
                System.out.println(answer);
            }
        }
    }
}