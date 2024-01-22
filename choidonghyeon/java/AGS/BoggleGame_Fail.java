/**
 * https://algospot.com/judge/submission/recent/
 * 문제 이름 : BoggleGame
 * 문제 결과 : 실패
 * 소요 시간(정답까지) : 1시간 30분
 * 사용 알고리즘 : DFS, Trie
 * 실패 이유 : 시간초과, 오답
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Node {
    Map<Character, Node> childMap = new HashMap<>();
    boolean isEnd;
}

class Trie {
    Node root = new Node();

    public void add(String word) {
        Node curr = this.root;
        for (int i = 0; i < word.length(); i++) {
            curr = curr.childMap.computeIfAbsent(word.charAt(i), key -> new Node());
        }
        curr.isEnd = true;
    }

    public int hasWord(String word) {
        Node curr = this.root;
        for (int i = 0; i < word.length(); i++) {
            if (curr.childMap.containsKey(word.charAt(i))) {
                curr = curr.childMap.get(word.charAt(i));
                continue;
            }
            return -1;
        }
        if (curr.isEnd) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return root.childMap.toString();
    }
}


class Main {
    static Trie trie;
    static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[][] board = new String[5][5];
            for (int j = 0; j < 5; j++) {
                board[j] = br.readLine().split("");
            }
            trie = new Trie();

            int words = Integer.parseInt(br.readLine());
            String[] wordsList = new String[words];

            for (int k = 0; k < words; k++) {
                String word = br.readLine();
                trie.add(word);
                wordsList[k] = word;
            }

            HashMap<String, Integer> answerDict = new HashMap<>();
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    dfs(y, x, board[y][x], board, answerDict);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (String word: wordsList) {
                String result = answerDict.containsKey(word) ? "YES" : "NO";
                sb.append(word);
                sb.append(" ");
                sb.append(result);
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }

    private static void dfs(int y, int x, String str, String[][] board, HashMap<String, Integer> answerDict) {
        for (int[] d : delta) {
            int my = y + d[0];
            int mx = x + d[1];

            if (0 <= my && my < 5 && 0 <= mx && mx < 5) {
                String newWord = str + board[my][mx];
                int result = trie.hasWord(newWord);
                if (result == 0) {
                    dfs(my, mx, newWord, board,answerDict);
                } else if (result == 1) {
                    answerDict.put(newWord,1);
                }
            }
        }
    }

}
