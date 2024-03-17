package first.Week2.AGS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Picnic {
    static boolean[][] friends;
    static boolean[] taken;
    static int c, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < c; i++) {
            friends = new boolean[10][10];
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); // 학생 수
            m = Integer.parseInt(st.nextToken()); // 친구 쌍의 수

            /*
            br.readLine으로 읽어온 뒤 st를 통해 토큰화를 진행함.
             */
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int f1 = Integer.parseInt(st.nextToken());
                int f2 = Integer.parseInt(st.nextToken());
                friends[f1][f2] = true;
            }
            taken = new boolean[10];
            System.out.println(countFriends(taken));
        }
    }

    static int countFriends(boolean[] taken) {
        int first = -1;
        for (int i = 0; i < n; i++) {
            if (!taken[i]) {
                first = i;
                break;
            }
        }
        // first가 -1이라는 것은 taken의 모든 인자가 true(짝이 된 상태)라는 것이다. 그러므로 return 1을 한다.
        if (first == -1) {
            return 1;
        }
        int res = 0;
        /*
        friends[first][i] && !taken[i] 조건문을 통해 친구 관계가 존재하지만 아직 친구를 찾지 못한 경우를 찾았다.
        친구 관계를 맺은 두 학생을 true로 변환한 뒤, 재귀 함수를 통해 완전 탐색을 구현하였습니다.
         */
        for (int i = first + 1; i < n; i++) {
            if (friends[first][i] && !taken[i]) {
                taken[first] = taken[i] = true;
                res += countFriends(taken);
                taken[first] = taken[i] = false;
            }
        }
        return res;
    }
}