package JavaCodingTestStudy.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1863V2 {
    static Stack<Integer> building; // stack을 활용하여 건물의 고도가 변하는 것을 확인
    static int [] locate; //빌딩의 고도를 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        locate = new int[50001];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tempx = Integer.parseInt(st.nextToken());
            int tempy = Integer.parseInt(st.nextToken());
            locate[i] = tempy;
        }
        building = new Stack<>();
        int buildingCount = 0;

        for(int i = 0; i<=n; i++){ //마지막에 push한 좌표가 있을 수도 있으므로 조건이 i<=n이다
            //stack이 비어있지 않고 스택의 최상위에 있는 값이 다음 좌표의 고도보다 작으면 pop
            while(!building.isEmpty() && building.peek()<locate[i]){
                building.pop();
                buildingCount++;
            }
            if(!building.isEmpty() && building.peek() == locate[i]){
                //stack의 최상위에 있는 값과 다음 좌표의 고도가 같다면 같은 건물이므로 push하지 않고 continue
                continue;
            }
            building.push(locate[i]);
        }
        System.out.println(buildingCount);
    }
}
