package jmb;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boggle {

    private static boolean[][][] cache;
    private static String[][] board;
    private static String[] words;
    private static int[] directionX = {-1, -1, -1, 1, 1, 1, 0, 0};
    private static int[] directionY = {-1, 0, 1, -1, 0, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int row = 5;
        int col = 5;

        int testCase = Integer.parseInt(bf.readLine());

        while (testCase-- > 0) {
            board = new String[row][col];

            for (int r = 0; r < row; r++) {
                String [] word = bf.readLine().split("");
                for (int c = 0; c < col; c++) {
                    board[r][c] = word[c];
                }
            }

            int wordCount = Integer.parseInt(bf.readLine());
            words = new String[wordCount];
            for (int count = 0; count < wordCount; count++) {
                words[count] = bf.readLine();
            }

            for (int count = 0; count < wordCount; count++) {
                String result = " ";
                String word = words[count];
                cache = new boolean[board.length][board.length][word.length()];
                if (search(word)) {
                    result += "YES";
                } else {
                    result += "NO";
                }
                System.out.println(word + result);
            }
        }
    }

    private static boolean search(String word) {
        for (int startRow = 0; startRow < board.length; startRow++) {
            for (int startCol = 0; startCol < board[startRow].length; startCol++) {
                boolean hasWord = hasWord(startRow, startCol, 0, word);
                if (hasWord) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasWord(int row, int col, int wordIndex, String word) {
        if (!isRange(row, col)) return false;
        if (cache[row][col][wordIndex]) return false;
        String w = board[row][col];
        if (word.charAt(wordIndex) != w.charAt(0)) return false;
        if (word.length() - 1 == wordIndex) return true;

        cache[row][col][wordIndex] = true;

        int length = directionX.length;
        for (int direction = 0; direction < length; direction++) {
            int nextRow = row + directionX[direction];
            int nextCol = col + directionY[direction];
            if (hasWord(nextRow, nextCol, wordIndex + 1, word)) {
                return true;
            }
        }



        return false;
    }

    private static boolean isRange(int row, int col) {
        if (row >= 5 || row < 0) return false;
        if (col >= 5 || col < 0) return false;
        return true;
    }
}



