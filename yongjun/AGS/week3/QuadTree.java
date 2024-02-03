package AGS.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.StringCharacterIterator;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class QuadTree {
    static String pic;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());

        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());
            pic = st.nextToken();
            StringCharacterIterator iterator = new StringCharacterIterator(pic);
            System.out.println(reverse(iterator));
        }
    }

    static String reverse(StringCharacterIterator picture) {
        char head = picture.current();
        picture.next();
        /*
        head의 값이 w or b이면 char 값을 반환한다.
        이 조건문을 통해 만약 head의 값이 x이면 return을 하지 않고 재귀 함수를 호출하게 한다.
         */
        if (head == 'w' || head == 'b') {
            return String.valueOf(head);
        }

        String topLeft = reverse(picture);
        String topRight = reverse(picture);
        String bottomLeft = reverse(picture);
        String bottomRignt = reverse(picture);

        return "x" + bottomLeft + bottomRignt + topLeft + topRight;
    }
}