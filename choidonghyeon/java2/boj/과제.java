import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 과제 {
	static class Assignment implements Comparable<Assignment> {
		int limit;
		int score;

		public Assignment(int limit, int score) {
			this.limit = limit;
			this.score = score;
		}

		@Override
		public int compareTo(Assignment target) {
			if (this.score != target.score) {
				return Integer.compare(target.score, this.score);
			}

			return Integer.compare(this.limit, target.limit);
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;

		boolean[] assignedDays = new boolean[1001];
		Assignment[] assignments = new Assignment[N];
		assignedDays[0] = true; //0일은 제외

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			assignments[i] = new Assignment(d,w);
		}

		Arrays.sort(assignments); //1. 점수로 내림차순 2. 마감일 오름 차순

		for (Assignment assignment : assignments) {
			int targetDay = assignment.limit;

			while(targetDay > 0) { //왼쪽방향으로 과제 수행가능 여부 확인
				if (!assignedDays[targetDay]) {
					answer += assignment.score;
					assignedDays[targetDay] = true;
					break;
				}
				targetDay--;
			}
		}

		System.out.println(answer);
	}
}
