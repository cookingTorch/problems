import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTree {
    static class Node {
        char value;
        boolean isLeaf;
        Node[] children; //1사분면 -> index 0  2사분면 -> index 1 ...

        public Node(char value) {
            this.value = value;
            if (value == 'x') { //현재 문자가 'x'이면 리프노드가 아니며 자식 노드를 4개 가지게 된다.
                isLeaf = false;
                children = new Node[4];
            } else { //현재 문자가 'b' , 'w'이면 리프노드이며 자식 노드는 없다.
                isLeaf = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String picture = br.readLine();
            Node root = new Node(picture.charAt(0)); //가장 첫번째를 루트 노드로 잡는다.
            sb.append(root.value);

            if (!root.isLeaf) { //루트노드가 리프노드가 아니라면
                makeTree(root, picture, 1); //주어진 문자열로 트리를 만든다.
                order(root, sb); // 트리를 상하를 뒤집은 순서로 순회한다.
            }
            sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    public static int makeTree(Node node, String picture, int idx) { //노드만 가지고 트리를 만드는 메서드
        int curr_idx = idx; //pointer 유지

        for (int i = 0; i < 4; i++) {
            node.children[i] = new Node(picture.charAt(curr_idx)); //현재 인덱스의 문자로 노드 생성
            if (node.children[i].isLeaf) { // 리프 노드이면 다음 문자열로 진행
                curr_idx++;
                continue;
            }
            curr_idx = makeTree(node.children[i], picture, curr_idx + 1); // 리프노드가 아니면('x'이면) 재귀로 하위 트리 생성
        }
        return curr_idx;
    }

    public static void order(Node node,StringBuilder sb) {  // 상하 뒤집어서 순회
        for (int i = 0; i < 4; i++) {
            int idx = (i + 2) % 4;  //상하를 뒤집었으므로 기존 자식노드 인덱스를 0 1 2 3에서  2 3 0 1 로 순회해야한다.
            Node child = node.children[idx];
            sb.append(child.value);

            if (!child.isLeaf) { //child가 리프 노드가 아니라면('x'라면) 계속 순회
                order(node.children[idx],sb);
            }
        }
    }
}
