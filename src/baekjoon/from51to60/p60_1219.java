package baekjoon.from51to60;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p60_1219 {
    private static final long MAX = 999_999_999_999l;
    private static final long MIN = -999_999_999_999l;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int cityCount = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());
        int transportCount = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(cityCount, transportCount);
        long[] maxCost = new long[cityCount];
        Arrays.fill(maxCost, MIN);
        for(int i=0; i<transportCount; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.start[i] = from;
            graph.end[i] = to;
            graph.cost[i] = cost;
        }
        //마지막줄: 각 노드에 도착했을 때 얼마 이득인지
        st = new StringTokenizer(br.readLine());
        int[] benefit = new int[cityCount];
        for(int i=0; i<cityCount; i++){
            benefit[i] = Integer.parseInt(st.nextToken());
        }
        graph.setBenefit(benefit);
        int ret = bellmanFord(startCity, endCity, graph, maxCost);
        long val = maxCost[endCity];
        if(ret==1){
            bw.write("Gee");
            bw.close();
            br.close();
            return;
        }
        if(ret==-1){
            bw.write("gg");
            bw.close();
            br.close();
            return;
        }

        bw.write(Long.toString(val));
        bw.close();
        br.close();

    }

    private static int bellmanFord(int start, int end, Graph graph, long[] maxCost){
        // 돈을 무한으로 벌 수 있으면 1,
        // 해당 도시에 도달할 수 없으면 -1
        // 음의 사이클 없이 해당 도시 도달 가능하면 0

        maxCost[start] = graph.benefit[start];
        for(int v=0; v<=graph.cityCount+100; v++){
            //N-1번 반복하면서 업데이트하기
            for(int e=0; e<graph.transportCount; e++){

                //업데이트 조건: 출발지가 사용가능하고(!=INF) 버는돈이 기존보다 더 크면 업데이트
                int startNode = graph.start[e];
                int endNode = graph.end[e];
                int cost = graph.cost[e];
                if(maxCost[startNode]==MIN){
                    //skip
                }else if(maxCost[startNode]==MAX){
                    maxCost[endNode]=MAX;
                }else if(maxCost[endNode]<maxCost[startNode]-cost+graph.benefit[endNode]){
                    maxCost[endNode]=maxCost[startNode]-cost+graph.benefit[endNode];
                    if(v >= graph.cityCount-1) maxCost[endNode] = MAX;
                }
            }
        }

        // N번째 업데이트 -> 업데이트가 가능하면 maxCost를 무한대로 만듦

        if(maxCost[end]==MAX) return 1;
        if(maxCost[end]==MIN) return -1;
        return 0;
    }


    static class Graph{
        int cityCount;
        int transportCount;
        int[] start;
        int[] end;
        int[] cost;
        int[] benefit;

        private Graph(int cityCount, int transportCount){
            this.cityCount = cityCount;
            this.transportCount = transportCount;
            this.start = new int[transportCount];
            this.end = new int[transportCount];
            this.cost = new int[transportCount];
        }
        private void setBenefit(int[] benefit){
            this.benefit = benefit;
        }
    }
}
