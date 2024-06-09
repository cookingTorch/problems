package JavaCodingTestStudy.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15685 {

    static boolean xy[][] = new boolean[101][101];
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,-1,0,1};
    //문제에서 x,y 반대로 나와서 반대로 설정
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int time = Integer.parseInt(br.readLine()); //횟수 설정
        Node list[] = new Node[time];
        for(int i = 0; i<time; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()); //요소 입력받기
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());
            list[i] = new Node(x, y, dir, gen);
            solve(list[i]);
        }
        int answer = find();
        System.out.println(answer);

    }
    public static void solve(Node node){
        List<Integer>list = new ArrayList<>();
        list.add(node.dir); // 0세대
        for(int i = 1; i<=node.gen; i++){ //k세대의 다음 세대인 k+1세대는 1~K세대를 반대로 한 방향을 갖기 때문에 반대로 반복후 방향을 list에 저장
            for(int j = list.size()-1; j>=0; j--)
                list.add((list.get(j)+1)%4);
        }
        xy[node.y][node.x] = true; //처음 위치 잡기
        int xx=node.x,yy=node.y;
        for(int i=0; i<list.size(); i++){ // 이동한 좌표를 true로 바꿔주는 작업
            int dir = list.get(i);
            xx += dx[dir];
            yy += dy[dir];
            xy[yy][xx] = true;
        }
    }
    public static int find(){
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (!xy[i][j]) continue;
                if (xy[i][j] && xy[i + 1][j] && xy[i][j + 1] && xy[i + 1][j + 1])
                    count++;
            }
        }
        return count;
    }
}
class Node{ //x,y좌표 방향 세대
    int x,y,dir,gen;
    public Node(int x,int y,int dir,int gen){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gen = gen;
    }
}
