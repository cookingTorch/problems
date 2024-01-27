import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Picnic {
    static int n;
    static int m;
    static int[][] friends;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            friends = new int[m][2];  // [[0,1],[1,2],[0,3] ...]

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                friends[j][0] = Integer.parseInt(st.nextToken());
                friends[j][1] = Integer.parseInt(st.nextToken());
            }
            cnt = 0; //정적 변수 초기화
            backtrack(0, new HashSet<>());
            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void backtrack(int idx, Set<Integer> studentSet) {
        if (studentSet.size() == n) {
            cnt += 1;
            return;
        }

        for (int i = idx; i < m; i++) {
//            if (studentSet.size() + (m - i) * 2 < n) { 남은 친구쌍을 모두 선택해도 총 학생수를 채울수없다면 종료
//                return;
//            } 해당 코드 있을시 112ms 없을시 96ms

            if (studentSet.contains(friends[i][0]) || studentSet.contains(friends[i][1])) {
                continue;
            }

            studentSet.add(friends[i][0]);
            studentSet.add(friends[i][1]);

            backtrack(i + 1, studentSet);

            studentSet.remove(friends[i][0]);
            studentSet.remove(friends[i][1]);
        }
    }
}
