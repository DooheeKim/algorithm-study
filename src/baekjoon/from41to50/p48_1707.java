package baekjoon.from41to50;

import java.io.*;
import java.util.*;

public class p48_1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        String[] ret;
        int countTestcases = Integer.parseInt(br.readLine());
        ret = new String[countTestcases];
        for(int tc=0; tc<countTestcases; tc++){
            st = new StringTokenizer(br.readLine());
            int countNodes = Integer.parseInt(st.nextToken());
            int countEdges = Integer.parseInt(st.nextToken());
            //그래프 초기화
            List<Integer>[] graph= new ArrayList[countNodes+1];
            int[] visited = new int[countNodes+1];

            for(int i=1; i<=countNodes; i++){
                graph[i] = new ArrayList<>();
            }
            for(int i=0; i<countEdges; i++){
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                //양방향 그래프가 아닌듯..?
                graph[n1].add(n2);
//                graph[n2].add(n1);
            }

            ret[tc] = isBipartite(graph, visited);
        }
        for(int i=0; i<countTestcases; i++){
            bw.write(ret[i]);
            bw.write("\n");
        }
        bw.close();
        br.close();
    }
    public static String isBipartite(List<Integer>[] graph, int[] visited){
        boolean res = false;

        int node = 1;
        while(node<visited.length){
            if(visited[node]==0){
                res = bfs(graph, visited, node);
                if(!res){
                    return "NO";
                }
            }
            node++;
        }
        return "YES";
    }

    public static boolean bfs(List<Integer>[] graph, int[] visited, int startNode){
        Queue<Integer> queue = new LinkedList<>(); //[node, color(1, -1)]
        queue.add(startNode);
        visited[startNode] = 1;// 임의로 첫번째 색깔을 1로 칠함
        if(graph.length==2) return false;
        while(!queue.isEmpty()){
            int currentNode = queue.poll();
            for(int nextNode:graph[currentNode]){
                if(visited[nextNode]==0){ // 방문한적 없으면 0
                    queue.add(nextNode);
                    visited[nextNode] = visited[currentNode]*(-1);// 반대 색으로 칠하기
                }else{
                    if(visited[currentNode] == visited[nextNode]){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
