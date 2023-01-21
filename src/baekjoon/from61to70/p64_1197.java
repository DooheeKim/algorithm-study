package baekjoon.from61to70;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class p64_1197 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());
        int[] start = new int[edgeCount];
        int[] end = new int[edgeCount];;
        int[] cost = new int[edgeCount];;

        for(int i=0; i<edgeCount;i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int costVal = Integer.parseInt(st.nextToken());
            start[i] = n1;
            end[i] = n2;
            cost[i] = costVal;
        }
        Graph graph = new Graph(nodeCount, edgeCount, start, end, cost);
        int ret = minSpanTree(graph);
        bw.write(Integer.toString(ret));
        bw.close();
        br.close();

    }
    private static int findParent(int node, Graph graph){
        if(graph.parent[node]==node) return node;
        return graph.parent[node] = findParent(graph.parent[node], graph);
    }
    private static void unite(int node1, int node2, Graph graph){
        int parent1 = findParent(node1, graph);
        int parent2 = findParent(node2, graph);

        if(parent1<parent2) graph.parent[parent2] = graph.parent[parent1];
        else graph.parent[parent1] = graph.parent[parent2];
    }
    private static int minSpanTree(Graph graph){
        int ret = 0;
        for(int i=0; i<graph.edgeCount; i++){
            if(findParent(graph.start[i],graph)!=findParent(graph.end[i],graph)){
                unite(graph.start[i], graph.end[i], graph);
                ret+=graph.cost[i];
            }
        }
        return ret;
    }
    private static class Graph{
        int nodeCount;
        int edgeCount;
        int[] start;
        int[] end;
        int[] cost;
        int[] parent; // Union-find 결과를 저장할 array

        private Graph(int nodeCount, int edgeCount, int[] start, int[] end, int[] cost){
            this.nodeCount = nodeCount;
            this.edgeCount = edgeCount;
            this.parent = new int[nodeCount+1];
            for(int i=0; i<=nodeCount; i++){
                this.parent[i] = i; // union-find 결과 초기화
            }

            this.start = new int[edgeCount];
            this.end = new int[edgeCount];
            this.cost = new int[edgeCount];

            Integer[] index = new Integer[edgeCount];
            for(int i=0; i<edgeCount; i++){
                index[i] = i;
            }
            Arrays.sort(index, new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(cost[o1], cost[o2]);
                }
            });
            int i = 0;
            for(int idx:index){
                this.start[i] = start[idx];
                this.end[i] = end[idx];
                this.cost[i] = cost[idx];
                i++;
            }
        }
    }
}
