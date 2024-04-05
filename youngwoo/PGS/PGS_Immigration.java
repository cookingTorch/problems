import java.util.Arrays;
public class PGS_Immigration {
    public long solution(int n, int[] times) {
        //이분 탐색 1부터 시작. 초기값 = 1
        long left = 1;
        //탐색 범위의 최대값. 초기값 = 입국자 수 * 가장 오래 걸리는 심사대의 시간
        long right = (long)n * (long)times[times.length - 1];
        //초기값 = right
        long answer = right;

        //탐색 범위가 남아있으면 계속 진행
        while (left <= right) {
            //탐색범위 중간값
            long mid = (left + right) / 2;
            //total <- mid 시간 안에 처리할 수 있는 입국자 수를 누적
            long total = 0;

            //각 심사대의 처리 시간
            for (int time : times) {
                //mid 시간 안에 처리할 수 있는 인원수를 계산한다
                total += mid / time;
            }

            //시간 안에 모든 사람을 처리할 수 있을 때
            if (total >= n) {
                //더 적은 시간이 나온 경우 answer 값을 갱신
                answer = Math.min(answer, mid);
                //탐색 범위 축소
                right = mid - 1;
            } else { //처리할 수 없다면 좌측 범위를 늘린다. 더 큰 시간이 필요한 것으로 간주.
                left = mid + 1;
            }
        }

        return answer;
    }
}