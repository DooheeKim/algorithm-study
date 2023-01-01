package baekjoon.from21to30;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p25_13023 {
    static boolean[][] adjacencyMatrix;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numNodes = Integer.parseInt(st.nextToken());
        int numEdges = Integer.parseInt(st.nextToken());

        adjacencyMatrix = new boolean[numNodes][numNodes];
        visited = new boolean[numNodes];
        for(int i=0; i<numEdges; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            adjacencyMatrix[node1][node2] = true;
            adjacencyMatrix[node2][node1] = true;
        }
        int ret=0;
        for(int i=0; i<numNodes; i++){
            Arrays.fill(visited, false); // 새로운 시작 할때마다 방문기록 초기화
            if(dfs(i, 0)){
                ret = 1;
                break;
            }
        }

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }

    public static boolean dfs(int node, int depth){
        //기저조건: 목표 depth 도달
        if(depth==4){
            return true;
        }
        for(int idx = 0; idx<adjacencyMatrix[node].length; idx++){
            //재귀 호출
            if(adjacencyMatrix[node][idx] && !visited[idx]){
                visited[idx] = true;
                if(dfs(idx, depth+1)) return true;
            }
        }

        return false;
    }
}
