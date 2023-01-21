package baekjoon.from51to60;

import java.io.*;
import java.util.*;

public class p54_1516 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int buildingCount = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new ArrayList[buildingCount+1];
        int[] inDegree = new int[buildingCount+1];
        int[] timeCost = new int[buildingCount+1];
        int[] actualTimeTaken = new int[buildingCount+1];
        for(int i=1; i<=buildingCount; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=1; i<=buildingCount; i++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());

            timeCost[i] = cost;
            while(st.countTokens()>0){
                int parent = Integer.parseInt(st.nextToken());
                if(parent>0){
                    graph[parent].add(i);
                    inDegree[i]++;
                }
            }
        }
        //여기까지 오면 그래프 + 각 건물별 코스트가 작성됨

        //위상정렬 용 큐 초기화
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=buildingCount; i++){
            if(inDegree[i]==0) queue.add(i);
        }
        // 본격적인 위상정렬 시작
        while(!queue.isEmpty()){
            int node = queue.poll();
            actualTimeTaken[node] += timeCost[node];

            for(Integer nextCandidate:graph[node]){
                actualTimeTaken[nextCandidate] = Integer.max(actualTimeTaken[node], actualTimeTaken[nextCandidate]);
                inDegree[nextCandidate]--;
                if(inDegree[nextCandidate]==0){
                    //queue에 추가
                    queue.add(nextCandidate);
                }
            }
        }
        for(int i=1; i<=buildingCount; i++){
            bw.write(Integer.toString(actualTimeTaken[i]));
            bw.write("\n");
        }
        bw.close();
        br.close();


    }
}
