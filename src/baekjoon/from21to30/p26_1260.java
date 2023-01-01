package baekjoon.from21to30;

import part1.ch08.PI;

import java.io.*;
import java.util.*;

public class p26_1260 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    static List<Integer> dfsVisitedOrder;
    static List<Integer> bfsVisitedOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numNodes = Integer.parseInt(st.nextToken());
        int numEdges = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        graph = new ArrayList[numNodes+1];
        visited = new boolean[numNodes+1];
        dfsVisitedOrder = new ArrayList<>();
        bfsVisitedOrder = new ArrayList<>();

        //초기화
        for(int i=1; i<=numNodes; i++){
            graph[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<numEdges; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        for(int i=1; i<=numNodes; i++){
            Collections.sort(graph[i]);
        }
        dfs(startNode);
        for(Integer visited:dfsVisitedOrder) {
            bw.write(Integer.toString(visited));
            bw.write(" ");
        }
        bw.write("\n");
        //visited 초기화
        Arrays.fill(visited, false);
        bfs(startNode);
        for(Integer visited:bfsVisitedOrder) {
            bw.write(Integer.toString(visited));
            bw.write(" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int node){
        //기저조건:  이미 방문한 노드면 그냥 리턴
        if(visited[node]) return;

        dfsVisitedOrder.add(node);
        visited[node] = true;
        for(int nextNode:graph[node]){
            dfs(nextNode);
        }
    }

    public static void bfs(int startNode){
        //queue로 구현해야됨
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        while(!queue.isEmpty()){
            int node =queue.poll();
            if(!visited[node]){
                visited[node] = true;
                bfsVisitedOrder.add(node);
            }

            for(int nextNode:graph[node]){
                if(!visited[nextNode]){
                    queue.add(nextNode);
                }
            }
        }
    }

}
