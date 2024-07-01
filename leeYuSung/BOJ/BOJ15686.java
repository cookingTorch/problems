package JavaCodingTestStudy.week4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class RowCol{
    int row,col;
    public RowCol(int row, int col){
        this.row = row;
        this.col = col;
    }
}
public class B15686 {

    static int M,N;
    static int [][]map;
    static  ArrayList<RowCol>chick;
    static  ArrayList<RowCol>citizen;
    static int answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N x N 맵을 받기 위함
        M = Integer.parseInt(st.nextToken());// 망하지 않는 최대 치킨집 수

        map = new int[N][N];
       chick = new ArrayList<>(); //치킨집의 정보를 가진 배열
       citizen = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2){
                    chick.add(new RowCol(i,j));
                }else if(map[i][j] == 1){
                    citizen.add(new RowCol(i,j));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[chick.size()];

        DFS(0,0);
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();

    }
    public static void DFS(int start, int count){
        if(count == M) {
            int res = 0; //현재 선택된 치킨집에서 모든 집까지의 최소 거리의 합

            for (int i = 0; i < citizen.size(); i++) { //시민의 집에서 치킨집까지의 거리를 측정
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < chick.size(); j++) {
                    if (visited[j]) { //현재 선택된 치킨집임
                        int distance = Math.abs(citizen.get(i).row - chick.get(j).row)
                                + Math.abs(citizen.get(i).col - chick.get(j).col);
                        temp = Math.min(temp, distance);//한 시민의 집에서 각 치킨집 까지의 가장 작은 거리를 구함
                    }
                }
                res += temp; //한 집에서 가장 적은 치킨집의 거리를 구해서 더함
            }
            answer = Math.min(res, answer);//만약 더 짧은 거리가 있다면 answer의 값을 변경
            return;
        }
            for(int p  = start; p < chick.size(); p++){
                visited[p] = true;
                DFS(p+1,count+1);
                visited[p] = false;
            }

    }
}

