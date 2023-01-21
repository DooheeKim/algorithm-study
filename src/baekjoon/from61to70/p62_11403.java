package baekjoon.from61to70;

import java.io.*;
import java.util.StringTokenizer;

public class p62_11403 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int nodeCount = Integer.parseInt(br.readLine());
        int[][] adjacencyMatrix = new int[nodeCount][nodeCount];
        for(int i=0; i<nodeCount;i++){
            st = new StringTokenizer(br.readLine());
            int val;
            for(int j=0; j<nodeCount; j++){
                val = Integer.parseInt(st.nextToken());
                adjacencyMatrix[i][j] = val;
            }
        }
        floydWarshall(adjacencyMatrix);
        for(int i=0; i<nodeCount; i++){
            for(int j=0; j<nodeCount; j++){
                bw.write(Integer.toString(adjacencyMatrix[i][j]));
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.close();
        br.close();
    }

    private static void floydWarshall(int[][] graph){
        int N = graph.length;
        for(int i=0; i<N;i++){
            //interval node
            for(int s = 0; s<N; s++){
                //start node;
                for(int e = 0; e<N; e++){
                    //end node
                    if(graph[s][e]==0 &&
                    graph[s][i]!=0 && graph[i][e]!=0){
                        graph[s][e] = 1;
                    }
                }
            }
        }
    }
}
