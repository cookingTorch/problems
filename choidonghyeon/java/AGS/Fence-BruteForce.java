import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * 완전탐색을 활용한 풀이
 * 시간복잡도: O(N**2) worstCase : 10000 * 20000
 * 실행속도: 1384ms
 */
public class Fence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] boards = new int[N]; //판의 높이를 순서대로
            Set<Integer> heights = new HashSet<>(); //판의 높이를 중복 제거해서 저장

            st = new StringTokenizer(br.readLine());
            int idx = 0;

            while(st.hasMoreTokens()) {
                int h = Integer.parseInt(st.nextToken());
                boards[idx++] = h;
                heights.add(h);
            }

            System.out.println(calculateMaxArea(boards,heights,N));
        }
    }

    private static int calculateMaxArea(int[] boards, Set<Integer> heights, int N) {
        int maxArea = 0; //최대 넓이를 계속 갱신
        int width = 0; // 해당 높이를 만족하는 연속되는 판의 개수(폭)
        for (int h : heights) {
            for (int i = 0; i < N; i++) {
                if (boards[i] >= h) { //현재 판이 타겟 높이 보다 크거나 같으면 width + 1
                    width ++;
                    continue;
                }
                maxArea = Math.max(maxArea, width * h); //현재 판이 타겟 높이 보다 작으면 최대넓이 갱신
                width = 0;
            }
            maxArea = Math.max(maxArea, width * h);
            width = 0;
        }
        return maxArea;
    }
}
