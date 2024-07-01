package JavaCodingTestStudy.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759 {
    static int L,C;
    static char code[];
    static char answer[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        code =new char[C];
        answer = new char[L];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < C ; i++){
            code[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(code);
        solution(0,0);

    }
    static void solution(int count,int start){
        //백트랙킹을 활용하여 들어갈 모음을 선택해주고 나머지를 선택하고 알아서 정렬하면 끝일것 같은데
        if(count == L){
            if(check()){
                System.out.println(answer);
            }
            return;
        }
        for(int i = start; i< C; i++){
            answer[count] = code[i];
            solution(count+1, i+1);
        }


    }
    public static boolean check() {
        int mo = 0;
        int ja = 0;

        for (char x : answer) {
            if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
                mo++;
            } else {
                ja++;
            }
        }

        if (mo >= 1 && ja >= 2) {
            return true;
        }
        return false;
    }
}
