package AGS;

// https://www.algospot.com/judge/problem/read/PICNIC
// 풀이참고 https://steady-coding.tistory.com/174

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Picnic {
    public static void main(String[] args) throws IOException {
        int caseNum, studentNum, pairNum;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringToken = new StringTokenizer(buffer.readLine());

            caseNum = Integer.parseInt(stringToken.nextToken());
            for (int i = 0; i < caseNum; ++i) {
                stringToken = new StringTokenizer(buffer.readLine());
                studentNum = Integer.parseInt(stringToken.nextToken());
                pairNum = Integer.parseInt(stringToken.nextToken());

                boolean[][] pairs = new boolean[studentNum][studentNum];
                boolean[] used = new boolean[studentNum];

                for (int j = 0; j < studentNum; ++j)
                    used[j] = false;
                setPairs(pairs, studentNum, pairNum, buffer);
                int result = getResult(pairs, studentNum, used);
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setPairs(boolean[][] pairs, int studentNum, int pairNum, BufferedReader buffer) throws IOException {
        for (int i = 0; i < studentNum; ++i)
            for (int j = 0; j < studentNum; ++j)
                pairs[i][j] = false;

        StringTokenizer stringToken = new StringTokenizer(buffer.readLine());
        for (int i = 0; i < pairNum; ++i) {
            int student1 = Integer.parseInt(stringToken.nextToken());
            int student2 = Integer.parseInt(stringToken.nextToken());

            pairs[student1][student2] = true;
            pairs[student2][student1] = true;
        }
    }

    private static int getResult(boolean[][] pairs, int studentNum, boolean[] used) {
        int target = -1;

        for (int i = 0; i < studentNum; ++i) {
            if (!used[i]) {
                target = i;
                break;
            }
        }

        if (target == -1)
            return 1;

        int result = 0;

        for (int i = target + 1; i < studentNum; ++i) {
            if (!used[i] && pairs[target][i]) {
                used[target] = true;
                used[i] = true;
                result += getResult(pairs, studentNum, used);
                used[target] = false;
                used[i] = false;
            }
        }
        return result;
    }

}
