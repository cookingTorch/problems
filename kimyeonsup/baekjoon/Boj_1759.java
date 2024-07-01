package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Boj_1759 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] conditions = br.readLine().split(" ");
        String[] letters = br.readLine().split(" ");
        new ArrayDeque().pop();
        Arrays.sort(letters);
        new PasswordSearch().searchPasswordCases(Integer.parseInt(conditions[0]), 0, letters);
        br.close();
    }
}

class PasswordSearch {

    private String[] vowels = {"a", "e", "i", "o", "u"};
    private Deque<String> visited = new ArrayDeque<>();

    public void searchPasswordCases(int combineCount, int depth, String[] combineLetters) {

        if (combineCount == visited.size()) {
            if (isPassword(visited.toArray(new String[visited.size()]))) {
                printPasswordCase();
            }
            visited.pollLast();
            return;
        }

        int length = combineLetters.length - (combineCount - (visited.size() + 1));
        for (int i = depth; i < length; i++) {
            String letter = combineLetters[i];
            if (!visited.contains(letter)) {
                visited.add(letter);
                searchPasswordCases(combineCount, i + 1, combineLetters);
            }
        }
        visited.pollLast();
    }

    private void printPasswordCase() {
        StringBuffer buffer = new StringBuffer();
        visited.forEach(v -> buffer.append(v));
        System.out.println(buffer);
    }

    private boolean isPassword(String [] password) {
        int vowelCount = 0;
        for (String word : password) {
            if (Arrays.stream(vowels).anyMatch(v -> word.equals(v))){
                vowelCount++;
            }
        }
        int consonantCount = password.length - vowelCount;
        return vowelCount >= 1 && consonantCount >= 2;
    }
}