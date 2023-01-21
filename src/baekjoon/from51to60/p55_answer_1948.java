package baekjoon.from51to60;

import java.io.*;
import java.util.*;

public class p55_answer_1948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int cityCount = Integer.parseInt(br.readLine());
        int pathCount = Integer.parseInt(br.readLine());

        // 그래프 정의
        List<int[]>[] forwardGraph = new ArrayList[cityCount + 1]; // graph[city] = [{next city, cost to visit}]
        List<int[]>[] reverseGraph = new ArrayList[cityCount + 1]; // graph[city] = [{next city, cost to visit}]
        int[] inDegree = new int[cityCount+1];
        int[] maximumCost = new int[cityCount+1];

        for (int i = 1; i <= cityCount; i++) {
            //초기화
            forwardGraph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < pathCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            forwardGraph[start].add(new int[]{end, cost});
            reverseGraph[end].add(new int[]{start, cost});
            inDegree[end]++;
        }

        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int finishCity = Integer.parseInt(st.nextToken());


        /// queue 돌면서 순회하면서 maximumCost arr 만들기
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startCity, 0});
        while(!queue.isEmpty()){
            int[] node = queue.poll();//[node, cost]
            for(int[] next:forwardGraph[node[0]]){
                maximumCost[next[0]] = Math.max(maximumCost[node[0]]+next[1],maximumCost[next[0]]);
                inDegree[next[0]]--;
                if(inDegree[next[0]]==0){
                    queue.add(next);
                }
            }
        }

        int objVal = maximumCost[finishCity];
        List<Integer> reversePath = new ArrayList<>();
        queue.add(new int[]{finishCity, 0}); // 위에 작업이 제대로 끝났으면 큐가 비어있는게 정상이라 비우는 작업 X
        boolean[] visited = new boolean[cityCount+1];
        while(!queue.isEmpty()){
            int[] node = queue.poll();

            for(int[] prev:reverseGraph[node[0]]){
                if(maximumCost[prev[0]]+prev[1]==maximumCost[node[0]]){
                    reversePath.add(node[0]);
                    if(!visited[prev[0]]){
                        visited[prev[0]] = true;
                        queue.add(prev);
                    }
                }

            }
        }

        bw.write(Integer.toString(objVal));
        bw.write("\n");
        bw.write(Integer.toString(reversePath.size()));
        bw.close();
        br.close();
    }


}
