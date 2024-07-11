package JavaCodingTestStudy.week6;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int preNum = Integer.parseInt(st.nextToken());

        List<List<Integer>>list = new ArrayList<>();
        int []indegree = new int [N+1];

        for(int i =0; i<=N; i++){
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < preNum; i++){
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            list.get(pre).add(next);
            indegree[next]++;// 특정 문제의 번호보다 먼저 풀어야하는 문제의 개수
        }

        PriorityQueue<Integer>pq = new PriorityQueue<>();
        for(int i =1; i<=N; i++){
            if(indegree[i]==0){ //먼저 풀 문제가 없을 때
                pq.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            int now = pq.poll();
            sb.append(now + " ");

            // now와 연결된 문제가 있는지 확인
            for (int nextNum : list.get(now)) {
                //문제를 풀었기 때문에 다음보다 먼저 풀어야하는 문제 개수가 1줄어든다
                indegree[nextNum]--;
                //먼저 풀 문제가 없을 떄 새롭게 큐에 데이터 삽입
                if (indegree[nextNum] == 0) {
                    pq.offer(nextNum);
                }
            }

        }
        bw.write(sb.toString());
        bw.flush();
    }
}
