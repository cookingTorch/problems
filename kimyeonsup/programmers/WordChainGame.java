package pgs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordChainGame {

    private Set<String> duplicatedWord = new HashSet<>();

    public int[] solution(int n, String[] words) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int personNumber = 1;
        int number = 0;
        String prevWord = "";
        boolean isEnd = true;
        for (String word : words) {
            number++;
            if (!isChainWord(word, prevWord)) {
                isEnd = false;
                break;
            }

            duplicatedWord.add(word);
            personNumber++;
            if (personNumber > n) personNumber = 1;
            prevWord = word;
        }

        int order = number / n;
        order += number % n > 0 ? 1 : 0;

        int[] result = new int[]{personNumber, order};
        return isEnd ? new int[]{0, 0} : result;
    }

    // 끝말잇기를 계속 이어갈 수 있는지 확인하는 메서드
    private boolean isChainWord(String word, String target) {
        // 처음일 경우 true
        if (target.equals("")) return true;

        // word의 시작과 target의 끝이 같지 않을 경우 false
        if (word.charAt(0) != target.charAt(target.length() - 1)) return false;

        // 이미 언급된 단어인지 확인
        if (duplicatedWord.contains(word)) return false;

        return true;
    }
}
