package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CreatePassword {

    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static char[] inputAlpha;
    static char[] passWord;
    static int passLen;
    static int alphaCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffer.readLine());

        passLen = Integer.parseInt(token.nextToken(" "));
        alphaCnt = Integer.parseInt(token.nextToken());
        inputAlpha = new char[alphaCnt];
        passWord = new char[passLen];

        token = new StringTokenizer(buffer.readLine());
        for (int i = 0; i < alphaCnt; i++) {
            inputAlpha[i] = token.nextToken().charAt(0);
        }
        Arrays.sort(inputAlpha);

        backTrac(0, 0);
    }

    static void backTrac(int start, int depth) {
        if (depth == passLen) {
            if (isValid(passWord)) {
                System.out.println(passWord);
            }
            return;
        }

        for (int i = start; i < alphaCnt; i++) {
            passWord[depth] = inputAlpha[i];
            backTrac(i + 1, depth + 1);
        }
    }

    static boolean isValid(char[] passWord) {
        int vowel = 0, consonant = 0;

        for (char c : passWord) {
            if (isVowel(c)) {
                vowel += 1;
            } else {
                consonant += 1;
            }
        }

        return vowel >= 1 && consonant >= 2;
    }

    static boolean isVowel(char c) {
        for (char vowel : vowels) {
            if (vowel == c) {
                return true;
            }
        }
        return false;
    }
}
