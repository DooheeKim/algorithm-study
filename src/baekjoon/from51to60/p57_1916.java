package baekjoon.from51to60;

import java.io.*;
import java.util.*;

public class p57_1916 {
    static final int INF = 999_999_999;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int cityCount = Integer.parseInt(br.readLine());
        int busCount = Integer.parseInt(br.readLine());
        List<int[]>[] graph = new ArrayList[cityCount+1];
        int[] minCost = new int[cityCount+1];
        Arrays.fill(minCost, INF);
        for(int i=1; i<=cityCount; i++){
            graph[i] = new ArrayList<>();
        }

        // 그래프에 간선 채우기
        for(int i=0; i<busCount; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new int[]{to, cost});
        }

        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        dijkstra(startCity, endCity, graph, minCost);

        bw.write(Integer.toString(minCost[endCity]));
        bw.close();
        br.close();
    }

    public static void dijkstra(int startCity, int endCity, List<int[]>[] graph, int[] minCost){
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        priorityQueue.add(new int[]{startCity, 0});

        while(!priorityQueue.isEmpty()){
            int[] curr = priorityQueue.poll(); //[node, cost]
            if(minCost[curr[0]] != INF || minCost[curr[0]]<=curr[1] ) continue; // 이미 최솟값을 구한 노드면 패스
            minCost[curr[0]] = curr[1]; // cost 업데이트
            for(int[] next:graph[curr[0]]){
                priorityQueue.add(new int[]{next[0], next[1]+curr[1]});
            }

            if(minCost[endCity] != INF) return;
        }
    }
}
