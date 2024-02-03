import java.util.Scanner;

public class QuadTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();
        sc.nextLine(); // 개행 문자 처리

        for (int i = 0; i < c; i++) {
            String compressedQuadTree = sc.nextLine();
            String result = decompressAndFlip(compressedQuadTree);

            System.out.println(result);
        }
    }

    private static String decompressAndFlip(String compressedQuadTree) {
        char firstChar = compressedQuadTree.charAt(0);

        if (firstChar == 'w' || firstChar == 'b') {
            // 기본 케이스: 모든 픽셀이 동일한 색일 때
            return String.valueOf(firstChar);
        } else {
            // 재귀적으로 상하로 뒤집어서 쿼드 트리 압축
            String upperLeft = decompressAndFlip(compressedQuadTree.substring(1));
            String upperRight = decompressAndFlip(compressedQuadTree.substring(1 + upperLeft.length()));
            String lowerLeft = decompressAndFlip(compressedQuadTree.substring(1 + upperLeft.length() + upperRight.length()));
            String lowerRight = decompressAndFlip(compressedQuadTree.substring(1 + upperLeft.length() + upperRight.length() + lowerLeft.length()));

            // 상하로 뒤집은 결과를 반환
            return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
        }
    }
}