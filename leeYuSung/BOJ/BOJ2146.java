package JavaCodingTestStudy.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2146 {
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static Queue<int []>q;
    static int mapSize;
    static boolean visited[][];
    static int map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapSize = Integer.valueOf(br.readLine());
        map = new int[mapSize][mapSize];
        q = new LinkedList<>();
        visited = new boolean[mapSize][mapSize];
        for(int i = 0; i < mapSize; i++){ // 지도 입력 받기
            StringTokenizer st  = new StringTokenizer(br.readLine());
            for(int j = 0; j < mapSize; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        identifyIsland();

        int minBridge = Integer.MAX_VALUE;
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                if(map[i][j] > 0){
                    visited = new boolean[mapSize][mapSize];
                    int length = findBridge(j,i);
                    if(minBridge>length && length != -1)
                        minBridge = length;
                }

            }
        }
        System.out.println(minBridge-1);
    }
    static void identifyIsland(){//섬을 식별하기 위한 함수
        int idx = 2;
        for(int i = 0; i<mapSize; i++){
            for(int j = 0; j<mapSize; j++){
                if(visited[i][j] == false && map[i][j] != 0){//방문하지 않고 바다인 곳
                    map[i][j] = idx;
                    visited[i][j] = true;
                    q.add(new int[]{j,i});

                    while(!q.isEmpty()){ //BFS 탐색
                        int p[] = q.poll(); //현재 위치 가져오기
                        int px = p[0];
                        int py = p[1];
                        for(int k = 0; k < 4; k++){ //상하좌우로 움직여 육지인 부분을 큐에 넣는다.
                            int nx = px + dx[k];
                            int ny = py + dy[k];
                            if(nx<0 || nx>mapSize-1 || ny <0 || ny>mapSize-1)
                                continue; // 범위 체크
                            if(map[ny][nx] == 1 && !visited[ny][nx]){
                                map[ny][nx] = idx;//섬을 1이 아닌 다른 수로 식별
                                visited[ny][nx] = true; //방문 표시
                                q.add(new int[]{nx,ny}); //큐에 추가하며 탐색 진행
                            }
                        }
                    }
                    idx++;
                }
            }
        }
    }

    static int findBridge(int x, int y){
        q = new LinkedList<>();
        int nowNum = map[y][x]; //현재 대륙의 번호
        visited[y][x] = true; //현재 위치
        q.add(new int[]{x,y,0}); //현재 위치와 다리의 크기를 q에 삽입
        while(!q.isEmpty()) { //bfs 시작
            int n[] = q.poll(); //q에 있는 요소 반환
            int px = n[0]; //x좌표
            int py = n[1]; //y좌표
            int bridge = n[2]; //다리 길이
            if (map[py][px] != nowNum && map[py][px] != 0) { //위치가 처음 설정한 대륙 번호와 다를 때 -> 다른 대륙으로 간거임
                return bridge;
            }
            for(int k = 0; k < 4; k++){//상하좌우 움직이기
                int nx = px + dx[k];
                int ny = py + dy[k];
                if(nx<0 || nx>mapSize-1 || ny <0 || ny>mapSize-1)
                    continue; // 범위 체크
                if(visited[ny][nx] || map[ny][nx] == nowNum) //방문했거나 같은 대륙 continue
                    continue;
                visited[ny][nx] = true; //방문 표시 후
                q.add(new int[]{nx,ny,bridge+1}); //큐에 집어넣음
            }
        }
        return -1;
    }
}
