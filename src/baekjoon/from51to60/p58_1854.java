package baekjoon.from51to60;

import java.io.*;
import java.util.*;

public class p58_1854 {
    static int[][] minCost; // [cost, K] 를 저장할 어레이
    static final int INF = 999_999_999;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int cityCount = Integer.parseInt(st.nextToken());
        int pathCount = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken()); // 문제에서 주어지는 K 번째 최단경로

        List<int[]>[] graph = new ArrayList[cityCount+1];
        minCost = new int[cityCount+1][2];

        for(int i = 1; i<=cityCount; i++){
            graph[i] = new ArrayList<>();
            minCost[i] = new int[]{INF, 0};
        }

        for(int i=0; i<pathCount; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new int[] {to, cost});
        }
        dijkstraModified(1, graph, K);
        for(int i=1; i<=cityCount; i++){
            int val = minCost[i][0];
            int k = minCost[i][1];
            if(k!=K) bw.write("-1");
            else bw.write(Integer.toString(val));
            bw.write("\n");
        }
        bw.close();
        br.close();
    }

    public static void dijkstraModified(int startCity, List<int[]>[] graph, int K){
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        priorityQueue.add(new int[]{startCity, 0});

        while(!priorityQueue.isEmpty()){
            int[] curr = priorityQueue.poll();
            // 다음으로 진행을 안할 조건 -> K번 업데이트 됐으면 그만하자..
            if(minCost[curr[0]][1]==K) continue; //해당노드는 더이상 업데이트 할 필요가 없음
            minCost[curr[0]][0] = curr[1];
            minCost[curr[0]][1] += 1;
            for(int[] next: graph[curr[0]]){
               if(minCost[next[0]][1]<K)
                   priorityQueue.add(new int[]{next[0], next[1]+curr[1]});
            }
        }
    }
}
