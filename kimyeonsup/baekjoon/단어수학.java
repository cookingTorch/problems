package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 단어수학 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] words = new String[n];
        int[] alphabet = new int[27];
        for (int i = 0; i < words.length; i++) {
            words[i] = bf.readLine();
        }

        for (String word : words) {
            int digit = (int) Math.pow(10, word.length() - 1);
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char c = word.charAt(i);
                alphabet[c - 65] += digit;
                digit /= 10;
            }
        }

        Arrays.sort(alphabet);
        int sum = 0;
        int num = 9;
        int start = alphabet.length - 1;
        for (int i = start; i >= 0; i--) {
            if (alphabet[i] == 0) break;

            sum += alphabet[i] * num;
            num--;
        }
        System.out.println(sum);
    }
}
