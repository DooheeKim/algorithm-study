package baekjoon.from51to60;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p59_11657 {
    static final int INF = 999_999_999;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int cityCount = Integer.parseInt(st.nextToken());
        int busCount = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(cityCount, busCount);
        long[] minCost = new long[cityCount+1];
        Arrays.fill(minCost, INF); // 초기거리 전부 무한으로 초기화
        for(int i=0; i<busCount;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.start[i] = from;
            graph.end[i] = to;
            graph.cost[i] = cost;
        }
        boolean result = bellmanFord(1, graph, minCost);
        if(result){
            //음의 순환 존재하는 경우
            bw.write("-1");
            bw.close();
            br.close();
            return;
        }
        // 음의 순환이 존재하지 않는 경우
        for(int i=2; i<=cityCount; i++){ // start node인 1번 빼고 나머지
            long val = minCost[i];
            if(val==INF) bw.write("-1");
            else bw.write(Long.toString(val));
            bw.write("\n");
        }
        bw.close();
        br.close();
    }
    private static class Graph{
        int nodeCount;
        int edgeCount;
        int[] start;
        int[] end;
        int[] cost;

        private Graph(int nodeCount, int edgeCount){
            start = new int[edgeCount];
            end = new int[edgeCount];
            cost = new int[edgeCount];
            this.nodeCount = nodeCount;
            this.edgeCount = edgeCount;
        }
    }
    private static boolean bellmanFord(int startNode, Graph graph, long[] minCost){
        // 음의 순환이 존재하면 false return, 아니면 true return
        minCost[startNode] = 0;
        boolean negativeLoopExistence = false;
        for(int v=0; v<graph.nodeCount; v++){
            // bellman ford 알고리즘은 노드 갯수-1번만큼 순회 -> 최단거리는 아무리 먼 노드여도 n-1번 이내로 구성
            // n 번 순회하고 마지막 바퀴에 negativeLoopExistence 나오게
            negativeLoopExistence = false;
            for(int e=0; e < graph.edgeCount; e++){
                if(minCost[graph.start[e]]!=INF && minCost[graph.end[e]]>minCost[graph.start[e]]+graph.cost[e]){
                    minCost[graph.end[e]]=minCost[graph.start[e]]+graph.cost[e];
                    negativeLoopExistence = true; // 하나라도 업데이트되면 true로 바꾸긴
                }
            }
        }
        return negativeLoopExistence;
    }
}
