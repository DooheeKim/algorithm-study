package baekjoon.from61to70;

import java.io.*;
import java.util.StringTokenizer;

public class p63_1389 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int userCount = Integer.parseInt(st.nextToken());
        int relationCount = Integer.parseInt(st.nextToken());
        int[][] adjacencyMatrix = new int[userCount+1][userCount+1];
        for(int i=0; i<relationCount; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            adjacencyMatrix[n1][n2] = 1;
            adjacencyMatrix[n2][n1] = 1;
        }
        floydWarshall(adjacencyMatrix);
        int min = 999_999_999; // 초깃값: 매우 큰 수
        int minUser = 0;
        for(int i=1; i<=userCount; i++){
            int userMin = 0;
            for(int j=1; j<=userCount; j++){
                userMin+=adjacencyMatrix[i][j];
            }
            if(min>userMin) {
                min = userMin;
                minUser = i;
            }
        }
        bw.write(Integer.toString(minUser));
        bw.close();
        br.close();
    }

    private static void floydWarshall(int[][] graph){
        int N = graph.length;
        for(int i=1; i<N; i++){
            // 거쳐갈 노드
            for(int s=1; s<N; s++){
                //시작 노드
                for(int e=1; e<N; e++){
                    //마지막 노드
                    if(graph[s][i]!=0 && graph[i][e]!=0){
                        int updateVal = graph[s][i]+graph[i][e];
                        if(graph[s][e]==0 || (graph[s][e]!=0 && (graph[s][e]>updateVal))){
                            graph[s][e] = updateVal;
                        }
                    }
                }
            }
        }
    }
}
