package JavaCodingTestStudy.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B19238 {
    /*
    1.map에서 가장 가까운 client를 찾는다
    2.가장 가까운 client를 목적지까지 보낸다
    3.목적지에서 가장 가까운 client를 찾는다
     */
    static int mapSize;
    static int clientNum;
    static int destination[][];
    static int map[][];
    static int fuel;
    static Taxi taxi;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        mapSize = Integer.parseInt(st.nextToken());
        clientNum = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[mapSize+1][mapSize+1];
        destination = new int[clientNum+1][2];//인원수와 목적지 저장

        for(int i = 1; i <=mapSize; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=mapSize; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int srow = Integer.parseInt(st.nextToken());
        int scol = Integer.parseInt(st.nextToken());
        taxi = new Taxi(fuel,srow,scol); // 연료량과 택시의 출발지를 받기
        for(int i = 1; i <= clientNum; i++){ //client의 시작 좌표와 목적 좌표 받기
            st = new StringTokenizer(br.readLine());
            int startClientRow = Integer.parseInt(st.nextToken());
            int startClientCol = Integer.parseInt(st.nextToken());
            map[startClientRow][startClientCol] = -i;
            destination[i][0] = Integer.parseInt(st.nextToken());
            destination[i][1] = Integer.parseInt(st.nextToken());
        }

        int cnt;
        for (cnt = clientNum; cnt > 0; cnt--) { //고객 수만큼 반복
            if (!find()) break;
        }
        if (cnt == 0)
            System.out.println(taxi.fuel);
        else
            System.out.println(-1);

    }
    static boolean find(){
        Queue<int[]>q = new LinkedList<>();
        Client client = null;
        boolean visited[][] = new boolean[mapSize+1][mapSize+1];
        q.add(new int[]{taxi.row, taxi.col});
        visited[taxi.row][taxi.col] = true;
        int length = 0;
        if (map[taxi.row][taxi.col] < 0) {
            q.poll();
            client = new Client(-map[taxi.row][taxi.col], taxi.row, taxi.col);
        }
        while(!q.isEmpty()){ //bfs시작
            if(client != null) //client를 찾았을 때 null이 아니게 됨, 이 조건이 없으면 계속 찾다가 연료 소진으로 -1 출력
                break;
            length++;
            int size = q.size();
            while (size-- > 0) { //현재 큐에 저장되어 있는 요소들만큼 반복
                int p[] = q.poll();
                int pr = p[0];
                int pc = p[1];
                for (int k = 0; k < 4; k++) {//상하좌우 움직이기
                    int mr = pr + dr[k];
                    int mc = pc + dc[k];
                    if (mr <= 0 || mr > mapSize || mc <= 0 || mc > mapSize) //범위 체크
                        continue;
                    if (visited[mr][mc] || map[mr][mc] == 1) //벽인지 혹은 방문했는지 확인
                        continue;
                    if (map[mr][mc] < 0) {
                        if (client == null || client.row > mr || (client.row == mr && client.col > mc)) {
                            client = new Client(-map[mr][mc], mr, mc);
                        }
                    }
                    visited[mr][mc] = true;
                    q.add(new int[]{mr, mc});
                }
            }
        }
        if(taxi.fuel< length || client == null) //손님 없음 + 연료 없음
            return false;
        taxi.fuel -= length; //원래 있던 가스에서 이동 거리를 뺌
        taxi.row = client.row; //택시의 위치를 손님한테 옮김
        taxi.col = client.col;
        map[taxi.row][taxi.col] = 0; //손님을 태웠으므로 기다리던 좌표를 0으로 변경

        return destiation(client.number);
    }
    static boolean destiation(int num){
        int destinationRow = destination[num][0];
        int destinationCol = destination[num][1];
        boolean visited[][] = new boolean[mapSize+1][mapSize+1]; //목적지까지 가는 거니깐 새로 boolean map
        Queue<int[]>q = new LinkedList<>();
        q.add(new int[]{taxi.row, taxi.col}); //taxi 위치( == client 위치)를 시작 좌표로
        visited[taxi.row][taxi.col] = true;
        int length = 0;
        boolean reached = false;

        while(!q.isEmpty() && !reached){
            length++;
            int size = q.size();
            while (size-- > 0) { //현재 큐에 저장되어 있는 요소들만큼 반복
                int temp[] = q.poll();
                int pr = temp[0];
                int pc = temp[1];
                for (int k = 0; k < 4; k++) { //상하좌우 이동
                    int mr = pr + dr[k];
                    int mc = pc + dc[k];
                    if (mr <= 0 || mr > mapSize || mc <= 0 || mc > mapSize) //범위 체크
                        continue;
                    if (visited[mr][mc] || map[mr][mc] == 1) //벽인지 혹은 방문했는지 확인
                        continue;
                    if (mr == destinationRow && mc == destinationCol) { //도착
                        reached = true;
                        break; //for문 탈출
                    }
                    visited[mr][mc] = true;
                    q.add(new int[]{mr, mc});
                }
            }
            if(reached == true)
                break; //while문 탈출
        }
        if (!reached || taxi.fuel < length)
            return false;
        taxi.fuel += length; //다시 충전
        taxi.row = destinationRow;
        taxi.col = destinationCol;
        return true;
    }
}
class Taxi{
    int fuel;
    int row;
    int col;
    public Taxi(int fuel, int row, int col) {
        this.fuel = fuel;
        this.row = row;
        this.col = col;
    }
}
class Client{
    int row;
    int col;
    int number;//손님을 구분해주는 숫자
    public Client(int number,int row, int col) {
        this.row = row;
        this.col = col;
        this.number = number;

    }
}