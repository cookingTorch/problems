public class 입국심사 {
	public long solution(int n, int[] times) {
		long answer = 0;

		long left = 1;
		long right = (long)Math.pow(10, 18);
		long mid;

		while (left <= right) {
			mid = (left + right) / 2;
			if (calculateMaxPeople(mid, times) >= n) { // mid의 시간안에 처리할 수 있는 최대인원수가 n보다 크거나 같다면
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return answer;
	}

	/**
	 * mid의 시간안에서 심사대의 심사위원들이 처리할 수 있는 대기인원의 최대 숫자를 구하는 메서드
	 */
	private long calculateMaxPeople(long mid, int[] times) {
		long cnt = 0;
		for (int time : times) {
			cnt += mid / time;
		}
		return cnt;
	}
}
