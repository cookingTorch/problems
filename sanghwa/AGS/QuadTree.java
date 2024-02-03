package AGS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class QuadTreeNode {
    boolean isLeaf;
    char value;
    QuadTreeNode[] child = new QuadTreeNode[4];

    QuadTreeNode(boolean isLeaf, char value) {
        this.isLeaf = isLeaf;
        this.value = value;
    }
}
public class QuadTree {
    private static int index, caseNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());
        caseNum = Integer.parseInt(token.nextToken());

        for (int i = 0; i < caseNum; ++i) {
            String data = buffer.readLine();
            index = 0;
            QuadTreeNode root = buildTree(data);
            root = flipTree(root);
            String flipped = compressTree(root);
            System.out.println(flipped);
        }
    }

    private static QuadTreeNode buildTree(String data) {
        char value = data.charAt(index++);
        if (value == 'x') {
            QuadTreeNode node = new QuadTreeNode(false, value);
            for (int i = 0; i < 4; ++i) {
                node.child[i] = buildTree(data);
            }
            return node;
        }
        return new QuadTreeNode(true, value);
    }

    private static QuadTreeNode flipTree(QuadTreeNode node) {
        if (node == null || node.isLeaf) return node;

        QuadTreeNode temp = node.child[0];
        node.child[0] = flipTree(node.child[2]);
        node.child[2] = flipTree(temp);
        temp = node.child[1];
        node.child[1] = flipTree(node.child[3]);
        node.child[3] = flipTree(temp);

        return node;
    }

    private static String compressTree(QuadTreeNode node) {
        if (node == null) return "";

        if (node.isLeaf)
            return String.valueOf(node.value);

        StringBuilder builder = new StringBuilder("x");
        for (int i =0; i < 4; ++i) {
            builder.append(compressTree(node.child[i]));
        }
        return builder.toString();
    }

}