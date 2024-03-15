import java.io.BufferedReader; //빠른 문자열 입력을 위한 클래스
import java.io.IOException;
import java.io.InputStreamReader; //바이트 기반 입력 스트림을 문자 기반 스트림으로 변경
import java.util.StringTokenizer;

public class BOJ_1244 {

	static int[] switches; //스위치의 상태(켜짐: 1, 꺼짐: 0)를 저장하는 배열

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 스위치 갯수
		switches = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			switches[i] = Integer.parseInt(st.nextToken()); // 스위치 초기 상태
		}

		int studentNum = Integer.parseInt(br.readLine()); // 학생 수

        //학생 수만큼 반복하며 gender에 따라 male 또는 female 함수 호출
		for (int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int gender = Integer.parseInt(st.nextToken()); // 성별
			int num = Integer.parseInt(st.nextToken()); // 스위치 번호

			if (gender == 1) {
				male(num);
			} else {
				female(num);
			}
		}
        //최종 스위치 상태를 20개씩 줄바꿈하여 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (i != 1 && i % 20 == 1) {
				sb.append("\n");
			}
			sb.append(switches[i]).append(" ");
		}
		System.out.println(sb.toString());

	}

    //자기 위치를 중심으로 좌우로 대칭인 스위치들 찾기 해당 대칭구간의 스위치 상태 반전
	private static void female(int idx) {

		int i = 1;
		int startIdx = idx;
		int endIdx = idx;

		while (true) {

			if (idx - i > 0 && idx + i < switches.length && switches[idx - i] == switches[idx + i]) {
				startIdx = idx - i;
				endIdx = idx + i;
				i++;
			} else {
				break;
			}

		}

		for (int j = startIdx; j <= endIdx; j++) {
			switches[j] = switches[j] == 0 ? 1 : 0;
		}

	}

    //스위치 번호의 배수에 해당하는 스위치의 상태를 반전
	private static void male(int idx) {

		int n = idx;

		while (idx < switches.length) {
			switches[idx] = switches[idx] == 0 ? 1 : 0;
			idx += n;
		}

	}

}