import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String wildCard = br.readLine();

            while(wildCard.contains("**")) {
                wildCard = wildCard.replace("**","*");
            }

            int N = Integer.parseInt(br.readLine());
            List<String> words = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                if (isMatch(wildCard, word,0,0)) {
                    words.add(word);
                }
            }
            words.stream().sorted().forEach(System.out::println);
        }
    }

    private static boolean isMatch(String wildCard, String word,int wildStart, int wordStart) {
        int currWordIdx = wordStart;

        /**
         * '*'의 범위를 지정하는 것.
         * 0~
         */

        for (int i = wildStart; i < wildCard.length(); i++) {
            if (wildCard.charAt(i) == '*') {
                if (i == wildCard.length() - 1) {
                    return true;
                }

                while (currWordIdx < word.length()) {
                    if (word.charAt(currWordIdx) == wildCard.charAt(i + 1)) {
                        if (isMatch(wildCard,word,i+1,currWordIdx)) {
                            return true;
                        }
                    }
                    currWordIdx += 1;
                }
                continue;
            }

            if (currWordIdx == word.length()) {
                return false;
            }

            if (wildCard.charAt(i) == '?') {
                currWordIdx += 1;
                continue;
            }

            if (wildCard.charAt(i) != word.charAt(currWordIdx)) {
                return false;
            }
            currWordIdx += 1;
        }
        if (currWordIdx != word.length()) {
            return false;
        }
        return true;
    }
}
