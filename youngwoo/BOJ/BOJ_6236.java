import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] expenses = new int[N];

        //최대 지출 초기화
        int maxExpense = 0;
        for (int i = 0; i < N; i++) {
            //각 날 지출 입력, 최대 지출 갱신
            expenses[i] = Integer.parseInt(br.readLine());
            maxExpense = Math.max(maxExpense, expenses[i]);
        }

        //최소 인출 금액 (최대 지출 감당)
        long minWithdrawal = maxExpense;
        //최대 인출 금액 (모든 지출 합산)
        long maxWithdrawal = 0;
        for (int expense : expenses) {
            maxWithdrawal += expense;
        }

        //최소 인출 금액과 최대 인출 금액이 만날 때까지 반복
        while (minWithdrawal <= maxWithdrawal) {
            //현재 검사하는 인출 금액
            long mid = (minWithdrawal + maxWithdrawal) / 2;

            //현재 금액으로 지출 관리 가능 여부 확인
            if (canManageExpenses(expenses, M, mid)) {
                maxWithdrawal = mid - 1;
            } else {
                //더 높은 금액으로 관리해야 하는지 확인
                minWithdrawal = mid + 1;
            }
        }

        System.out.println(minWithdrawal);
    }

    private static boolean canManageExpenses(int[] expenses, int M, long withdrawalAmount) {
        //현재까지 사용한 인출 횟수
        int withdrawalsUsed = 1;
        //현재까지 누적된 지출 금액
        long currentSum = 0;

        //각 지출 항목에 대해 반복
        for (int expense : expenses) {
            //지출 항목이 한 번 인출 금액보다 크면 False
            if (expense > withdrawalAmount) {
                return false;
            }

            //현재 누적 금액에 지출 항목 더하기
            currentSum += expense;
            //현재 누적 금액이 인출 금액보다 크면
            if (currentSum > withdrawalAmount) {
                //인출 횟수 증가
                withdrawalsUsed++;
                //다음 인출을 위해 누적 금액 초기화
                currentSum = expense;
            }
        }

        //사용 인출 횟수가 M 이하라면 True
        return withdrawalsUsed <= M;
    }
}