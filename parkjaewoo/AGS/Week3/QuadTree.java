package AGS.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

// 풀다보니 트리를 직접 구현해도 되지 않는다는 것을 알았지만, 풀어본 김에 쿼드트리를 구현해보았습니다.

public class QuadTree {
    static Tree tree;
    static int[] searchIdx = {2, 3, 0, 1}; // 그림을 상하로 뒤집는 다는 것은 주어진 쿼드트리의 1 <-> 3, 2 <-> 4로 바꿔주는 것을 의미합니다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb; // 뒤집은 문자열을 담을 StringBuilder입니다.

        int testCase = parseInt(br.readLine());

        while (testCase-- > 0) {
            String sentence = br.readLine();
            sb = new StringBuilder();

            if (sentence.charAt(0) == 'x') { // 만약 sentence의 첫 문자가 'x'이면 트리를 구성합니다.
                tree = new Tree();
                makeTree(sentence, 1, tree.head);
                swapTree(sb, tree.head);
            } else {                        // 그렇지 않다면 sentence는 'w' 혹은 'b'일 것이기때문에 그대로 프린트합니다.
                sb.append(sentence);
            }
            System.out.println(sb);
        }
    }
    // 먼저 받은 문자열을 토대로 쿼드 트리를 만들어 줍니다.
    private static int makeTree(String sentence, int idx, Node parent) {
        for (int i = 0; i < 4; i++) {
            if (sentence.charAt(idx + i) == 'x') {                   // 만약 sentence.charAt(idx + i)가 'x'일 경우,
                Node newNode = new Node('x', parent, null); // 새로운 노드를 만들어줍니다.
                parent.children[i] = newNode;                        // children[i]에 newNode를 연결함과 동시에
                parent.childNode = newNode;                          // parent의 childNode에 newNode를 연결합니다
                idx = makeTree(sentence, idx + i + 1, newNode) - i - 1; // 이후, 재귀함수를 호출합니다.
                                                                    // idx를 잘 전달하기 위해 idx + i + 1을 매개변수로 보내주었으므로 이후 - i - 1울 해줘야합니다.
            } else {
                parent.children[i] = new Node(sentence.charAt(idx + i)); // 만약 'x'가 아니면 children[i]에 'w'나 'b'를 담아줍니다.
            }
        }
        return idx + 4; // 재귀함수로 들어와서 for문을 4번 돌았으므로 idx + 4를 리턴합니다.
    }
    private static void swapTree(StringBuilder sb, Node node) {
        sb.append('x');
        for (int i = 0; i < 4; i++) { // searchIdx를 통해 쿼드트리를 뒤집습니다.
            if (node.children[searchIdx[i]].c == 'x') {
                swapTree(sb, node.children[searchIdx[i]]);
            } else {
                sb.append(node.children[searchIdx[i]].c);
            }
        }
    }
}

class Tree { // 트리의 구조는 head만 존재합니다.
    Node head;

    Tree() {
        head = new Node('x',null, null);
    }
}

class Node { // Node의 구조는 'x', 'w', 'b'를 담을 Character c, 부모 노드, 자식 노드를 가리키는 포인터, 그리고 노드 배열로 구성됩니다.
    Character c;
    Node parentNode;
    Node childNode;
    Node[] children;
    Node(Character c) {
        this.c = c;
        childNode = null;
    }

    Node(Character c, Node parent, Node child) {
        this.c = c;
        this.parentNode = parent;
        this.childNode = child;
        children = new Node[4]; // 문자가 'x'일 경우 자식 노드를 총 4개 갖게 됩니다.
    }
}