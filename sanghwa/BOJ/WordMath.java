package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WordMath {
    static int N;
    static int [] arr = new int[26];

    public static void main(String[] args) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(buffer.readLine());

        for (int i = 0; i < N; i++) {
            String line = buffer.readLine();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                arr[c - 'A'] += (int) Math.pow(10, line.length() - 1 - j);
            }
        }

        Arrays.sort(arr);

        int num = 9;
        int idx = 25;
        int answer = 0;
        while (arr[idx] != 0) {
            answer += arr[idx]*num;
            idx--;
            num--;
        }
        System.out.println(answer);
    }
}
