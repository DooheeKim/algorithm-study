package baekjoon.from51to60;

import java.io.*;
import java.util.*;

public class p56_1753 {
    static int[] minDistanceArr;
    static final int INF = 9999999;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());
        List<int[]>[] graph = new ArrayList[nodeCount+1];
        minDistanceArr = new int[nodeCount+1];
        Arrays.fill(minDistanceArr,INF);
        for(int i=1; i<=nodeCount; i++){
            graph[i] = new ArrayList<>();
        }

        int startNode = Integer.parseInt(br.readLine());
//        minDistanceArr[startNode] = 0;
        for(int i=0; i<edgeCount; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, weight});
        }

        dijkstra(startNode, graph);
        for(int i=1; i<=nodeCount; i++){
            if(minDistanceArr[i]==INF) bw.write("INF");
            else bw.write(Integer.toString(minDistanceArr[i]));
            bw.write("\n");
        }
        bw.close();
        br.close();
    }

    public static void dijkstra(int startNode, List<int[]>[] graph){
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        priorityQueue.add(new int[]{startNode, 0});
        while(!priorityQueue.isEmpty()){
            int[] curr = priorityQueue.poll();

            if(curr[1]>minDistanceArr[curr[0]] || minDistanceArr[curr[0]]!=INF) continue;

            minDistanceArr[curr[0]] = curr[1];
            for(int[] next:graph[curr[0]]){
                priorityQueue.add(new int[]{next[0], next[1]+curr[1]});
            }
        }
    }


}
