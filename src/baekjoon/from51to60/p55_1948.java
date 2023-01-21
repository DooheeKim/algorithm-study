package baekjoon.from51to60;

import java.io.*;
import java.util.*;

public class p55_1948 {
    // 메모리 에러 --- 역방향으로 도로 탐색하는거를 너무 노가다로 했다..
    private static long[] cache; // 마지막 도시까지 가는데 필요한 거리를 저장하는 arr
    private static PriorityQueue<Paths> priorityQueue;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int cityCount = Integer.parseInt(br.readLine());
        int pathCount = Integer.parseInt(br.readLine());
        cache = new long[cityCount+1];

        // 그래프 정의
        List<int[]>[] graph = new ArrayList[cityCount+1]; // graph[city] = [{next city, cost to visit}]
        for(int i=1; i<=cityCount;i++){
            //초기화
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<pathCount; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new int[]{end, cost});
        }

        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int finishCity = Integer.parseInt(st.nextToken());

        boolean[][] visitedPaths = new boolean[cityCount+1][cityCount+1]; // 길 갯수 셀때 사용할 어레이
        priorityQueue = new PriorityQueue<>();
        dfs(startCity, finishCity, 0, new Stack<>(), graph);
        int visitedPathsCounter = 0;

        long latestTime = priorityQueue.peek().cost;
        bw.write(Long.toString(latestTime));
        bw.write("\n");
        while(priorityQueue.size()>0){
            Paths paths = priorityQueue.poll();
            if(paths.cost<latestTime) break;

            int from = startCity;
            for(Integer to: paths.path){
                if(!visitedPaths[from][to]){
                    visitedPaths[from][to] = true;
                    visitedPathsCounter+=1;
                }
                from = to;
            }
        }
        bw.write(Integer.toString(visitedPathsCounter));
        bw.close();
        br.close();

    }
    private static long dfs(int node, int finishCity, long cost, Stack<Integer> path, List<int[]>[] graph){
        // 현재노드, 마지막 도시, 지금까지 든 시간, 지금까지 걸어온 경로
        if(node==finishCity || cache[node]>0){
//            path.add(node);
            List<Integer> savePath = new ArrayList<>();
            savePath.addAll(path);
            priorityQueue.add(new Paths(cost+cache[node],savePath));
//            path.pop();
            return 0;
        }

        long ret = 0;
        //다음노드 순회하면서 dfs
        for(int[] nextNode:graph[node]){
            path.add(nextNode[0]);
            ret = Long.max(ret, dfs(nextNode[0], finishCity, cost+nextNode[1], path, graph));
            path.pop();
        }
        // 캐시에 저장될 것은 앞으로 가야할 거리이므로..
        return cache[node] = (ret-cost);
    }

    private static class Paths implements Comparable<Paths>{
        long cost;
        List<Integer> path;
        private Paths(long cost, List<Integer> path){
            this.cost = cost;
            this.path = path;
        }

        @Override
        public int compareTo(Paths o) {
            return Long.compare(o.cost, this.cost); /// 큰게 먼저 와야되므로 순서 반대로 적음
        }
    }

}
