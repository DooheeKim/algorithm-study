package baekjoon.from21to30;

import java.io.*;
import java.util.*;

public class p25_13023_part2 {
    static Map<Integer, List<Integer>> graph;
    static int ret;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numNodes = Integer.parseInt(st.nextToken());
        int numEdges = Integer.parseInt(st.nextToken());

        visited = new boolean[numNodes];
        graph = new HashMap<>();
        for(int i=0; i<numNodes; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int i=0; i<numEdges; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        ret=0;
        for(int i=0; i<numNodes; i++){
            Arrays.fill(visited, false); // 새로운 시작 할때마다 방문기록 초기화
            if(dfs(i, 0)){
                break;
            }
        }

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }

    public static boolean dfs(int node, int depth){
        //기저조건: 목표 depth 도달
        if(depth==4 || ret==1){
            ret=1;
            return true;
        }
        visited[node] = true;
        for(int idx:graph.get(node)){
            if(!visited[idx]){
                if(dfs(idx, depth+1)) {
                    visited[node] = false;
                    return true;
                }
            }
        }
        visited[node] = false;
        return false;
    }
}
