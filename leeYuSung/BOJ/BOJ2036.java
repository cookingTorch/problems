package JavaCodingTestStudy.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class B2036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine()); //갯수
        ArrayList<Long> pArr = new ArrayList<Long>();
        ArrayList<Long> nArr = new ArrayList<Long>();
        int zero = 0;
        for (int i = 0; i < n; i++) {
            long a = Long.valueOf(br.readLine());
            if(a>0)
                pArr.add(a);
            else if(a<0)
                nArr.add(a);
            else if(a==0)
                zero++;
        }
        Collections.sort(nArr);
        Collections.sort(pArr, Collections.reverseOrder());
        Long answer = 0L;
        answer = nagativeSum(nArr,zero) + postiveSum(pArr);
        System.out.println(answer);

    }
    public static long nagativeSum(ArrayList<Long> nArr,int zero) {//음수에서는 0을 중점적을 생각
        long sum = 0;
        for(int i = 0; i<nArr.size(); i ++){
            if((nArr.size()-i) == 1){ //음수 개수가 홀수일 때
                if(zero>0) zero--;//음수가 1개 남았는데 0이 있으면 0을 곱해서 더하면 0이니깐 0갯수만 하나 줄이면 됌
                else sum += nArr.get(i);
            }else{
                sum += nArr.get(i)*nArr.get(i+1);
                i++;
            }
        }
        return sum;
    }
    public static long postiveSum(ArrayList<Long> pArr) {//양수에서는 1을 중점적으로 생각
        long sum = 0;
        for(int i = 0; i<pArr.size(); i ++){
            if(pArr.size()-i==1){ //양수 개수가 홀수일 때
                sum += pArr.get(i);
            }else{
                if(pArr.get(i)==1 || pArr.get(i+1) ==1){
                    sum += pArr.get(i);
                }else{
                    sum += pArr.get(i)*pArr.get(i+1);
                    i++;
                }
            }
        }
        return sum;
    }
}
/*
5
-10
-5
0
1
15
->
66
 */