import java.util.Scanner;

public class BOJ_16401 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt(); //조카의 수
        int N = sc.nextInt(); //과자의 수

        //과자 길이 배열
        int[] lengths = new int[N];
        for (int i = 0; i < N; i++) {
            lengths[i] = sc.nextInt();
        }

        //최소 길이, 최대 길이
        long minLength = 1;
        long maxLength = 0;
        for (int length : lengths) {
            maxLength += length;
        }

        //이분 탐색: 최대 길이 찾는다
        while (minLength <= maxLength) {
            //현재 탐색 길이 계산
            long mid = (minLength + maxLength) / 2;

            //mid 길이의 과자를 나누어 줄 수 있는지 확인
            if (canDivide(lengths, M, mid)) {
                //나누어 줄 수 있다면 최소 길이 = mid+1
                minLength = mid + 1;
            } else {
                //나누어 줄 수 없다면 최대 길이 = mid-1
                maxLength = mid - 1;
            }
        }
        //최대 길이 출력
        System.out.println(maxLength);
    }

    //주어질 길이의 과자를 조카들에게 나누어 줄 수 있는지 확인
    private static boolean canDivide(int[] lengths, int M, long length) {
        //과자 개수 저장 변수
        int count = 0;
        //모든 과자의 길이를 length로 나누어 과자의 개수를 계산
        for (int i : lengths) {
            count += i / length;
        }

        //계산된 과자의 개수가 조카의 수보다 크거나 같을 때, True 반환
        return count >= M;
    }
}