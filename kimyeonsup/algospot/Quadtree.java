package jmb;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quadtree {

    private static int[] searchIndexes = {2, 3, 0, 1}; // 상하 반전시 3 -> 1 , 4 -> 2 swap 된다.

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testcase = parseInt(bf.readLine());

        while (testcase-- > 0) {
            // init
            String result = "x"; // 'x 초기화'
            char[] quadTree = bf.readLine().toCharArray();

            // 재귀 함수 - 탐색 시작
            // 1 이상일 경우 무조건 index 5 이상
            if (quadTree.length > 1) {
                result += upDownQuadTree(1, quadTree);
            } else {
                // 1일 경우 맨 첫번째 값 그대로 출력
                result = String.valueOf(quadTree[0]);
            }

            // print
            System.out.println(result);
        }
    }

    private static String upDownQuadTree(int stringIndex, char[] quadTree) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < searchIndexes.length; i++) {

            int quadTreeIndex = calculateIndex(stringIndex, searchIndexes[i], quadTree);
            char c = quadTree[quadTreeIndex];

            // 'x' 이면 x 뒤에 4분할 그림 탐색
            if (c == 'x') {
                // x + '상하 좌우된 4분할 그림'
                sb.append(c);
                sb.append(upDownQuadTree(quadTreeIndex + 1, quadTree));
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    // calculateIndex => 확인할 문자를 찾기위한 index 계산 메서드
    // index 참색은 x후로 부터 3,4,1,2 순서로 탐색하나 이전 값들 중에 x가 있으면 x만큼 원래 자리에서 4가 늘어난다.
    // 최적화 방법 없나?
    private static int calculateIndex(int stringIndex, int searchIndex, char[] quadTree) {
        int index = stringIndex + searchIndex;
        for (int i = stringIndex; i < index; i++) {
            if (quadTree[i] == 'x') {
                index += 4;
            }
        }
        return index;
    }
}
