package baekjoon.from61to70;

import java.io.*;
import java.util.StringTokenizer;

public class p61_11404 {
    private static final int INF = 99_999_999;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int cityCount = Integer.parseInt(br.readLine());
        int busCount = Integer.parseInt(br.readLine());
        int[][] adjacencyMatrix = new int[cityCount+1][cityCount+1]; // 기본적으로 0으로 채워진 상태로 시작
        for(int i=1; i<=cityCount;i++){
            for(int j=1; j<=cityCount; j++){
                if(i!=j){
                    adjacencyMatrix[i][j] = INF;
                }
            }
        }

        for(int m=0; m<busCount; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjacencyMatrix[from][to] = Math.min(cost, adjacencyMatrix[from][to]);
        }
        floydWarshall(adjacencyMatrix);
        for(int i=1; i<=cityCount; i++){
            for(int j=1; j<=cityCount; j++){
                bw.write(Integer.toString(adjacencyMatrix[i][j]>=INF?0:adjacencyMatrix[i][j]));
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.close();
        br.close();
    }

    public static void floydWarshall(int[][] adjacencyMatrix){
        int N = adjacencyMatrix.length;
        for(int i=1; i<N; i++){
            // 중간에 거쳐갈 노드
            for(int s=1; s<N; s++){
                // 시작 노드
                for(int e=1; e<N; e++){
                    //종료 노드
                    adjacencyMatrix[s][e] = Math.min(adjacencyMatrix[s][e],
                            adjacencyMatrix[s][i]+adjacencyMatrix[i][e]);
                }
            }
        }
    }
}
