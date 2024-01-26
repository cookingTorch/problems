import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ClockSync {
    static int INF = 100000000;
    static int answer;
    static int[] clocks;
    static int pushCnt;
    static int[][] switches = new int[][]{
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13},
    };


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            clocks = new int[16];
            pushCnt = 0;
            answer = INF;

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 16; j++) {
                clocks[j] = (Integer.parseInt(st.nextToken()) / 3) % 4;  // 12 -> 0  3-> 1 6 -> 2 9 -> 3
            }
            solve(0);

            if (answer == INF) { //완전 탐색결과 조건을 만족하는 경우가 없다면
                answer = -1;
            }
            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int numSwitch) {
        if (numSwitch == 10) { ///마지막 스위치까지 다 따져봤다면
            calculateAnswer(); // 누른 횟수를 최신화하는 메서드
            return;
        }

        solve(numSwitch + 1);  //현재 스위치를 누르지 않는 경우 다음 스위치로 바로 진행
        for (int i = 0; i < 3; i++) {
            pushSwitch(numSwitch);
            pushCnt += 1;
            solve(numSwitch + 1);
        }
        pushSwitch(numSwitch); //3번의 스위치를 눌렀으므로 다시 원상 복귀 시키기위해 한번더 스위치 누름 (4번 누르면 맨처음상태로 원상복귀)
        pushCnt -= 3; //위에서 3번스위치를 누른 횟수를 다시 차감(백트래킹)
    }

    private static void pushSwitch(int numSwitch) {
        for(int clock : switches[numSwitch]) {
            clocks[clock] = (clocks[clock] + 1) % 4;
        }
    }

    private static void calculateAnswer() {
        for (int clock: clocks) {
            if (clock != 0) {
                return;
            }
        }
        answer = Math.min(answer,pushCnt);
    }
}
