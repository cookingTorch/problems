package PGS.SecondWeek1;

import java.util.Arrays;
import java.util.LinkedList;

public class WordRelay {
    public static void main(String[] args) {
        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        System.out.println(Arrays.toString(Solution.solution(5, words)));
    }
    static class Solution {
        public static int[] solution(int n, String[] words) {
            int[] answer = {0, 0};
            int idx = 0;
            boolean check = false;
            LinkedList<String> findBefore = new LinkedList<>();
            while (idx < words.length) {
                for (int i = 0; i < n; i++) {
                    for (String e : findBefore) {
                        if (e.equals(words[idx + i]) || check) {
                            answer[0] = i + 1;
                            answer[1] = (idx / n) + 1;
                            return answer;
                        }
                    }
                    if (idx + i + 1 < words.length && (words[idx + i].charAt(words[idx + i].length() - 1)
                            == words[idx + i + 1].charAt(0))) {
                        findBefore.add(words[idx + i]);
                    }
                    else if (idx + i + 1 == words.length &&
                            (words[words.length - 1].charAt(0) != words[words.length - 2].charAt(words[words.length -2].length() - 1))){
                        answer[0] = i + 1;
                        answer[1] = (idx / n) + 1;
                        return answer;
                    }
                    else if (idx + i + 1 == words.length) {
                        return answer;
                    }
                    else {
                        findBefore.add(words[idx + i]);
                        check = true;
                    }
                }
                idx += n;
            }
            return answer;
        }
    }
}
