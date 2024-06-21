import java.io.*;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class SWEA_9780
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int T;
        T=Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n;
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            long ans = 0, one, two;
            one = Long.parseLong(st.nextToken());
            if (n == 1) {
                System.out.println("#" + test_case + " " + one);
                continue;
            }

            two=Math.max(one, Long.parseLong(st.nextToken()));
            if (n == 2) {
                System.out.println("#" + test_case + " " + two);
                continue;
            }

            for (int i = 3; i <= n; i++) {
                ans = Math.max(Long.parseLong(st.nextToken()) + one, two);
                one = two;
                two = ans;
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }
}
