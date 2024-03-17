package PGS;

import java.util.Arrays;

public class EnglishWordRelay {

    public static void main(String[] args) {
        int[] result = solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});

        System.out.println(Arrays.toString(result));
    }
    public static int[] solution(int n, String[] words) {
        int[] answer = {0, 0};

        for (int endIdx = 1; endIdx < words.length; endIdx++) {
            char lastChar = words[endIdx - 1].charAt(words[endIdx - 1].length() - 1);
            if (lastChar != words[endIdx].charAt(0)) {
                answer[0] = (endIdx % n) + 1;
                answer[1] = (endIdx / n) + 1;
                break;
            }
            if (!checkOverlap(words, words[endIdx], endIdx)) {
                answer[0] = (endIdx % n) + 1;
                answer[1] = (endIdx / n) + 1;
                break;
            }
        }
        return answer;
    }

    public static boolean checkOverlap(String[] words, String target, int endIdx) {
        for (int i = 0; i < endIdx; i++) {
            if (words[i].equals(words[endIdx]))
                return false;
        }
        return true;
    }
}
