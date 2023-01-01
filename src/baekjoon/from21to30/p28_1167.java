package baekjoon.from21to30;

import java.io.*;
import java.util.*;

public class p28_1167 {
    private static class Node{
        int end;
        int weight;
        private Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }
    public static List<Node>[] graph;
    public static boolean[] visited;
    public static int ret = 0;
    public static void main(String[] args ) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numNodes = Integer.parseInt(br.readLine());
        StringTokenizer st;
        graph = new LinkedList[numNodes+1];
        visited= new boolean[numNodes+1];

        for(int i=1; i<=numNodes; i++){
            graph[i] = new LinkedList<>();
        }
        for(int i=0; i<numNodes; i++){
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while(st.countTokens()>1){
                int newNode = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[node].add(new Node(newNode, weight));
            }
        }
        int startNode =1; // tree라서 어디서 시작해도 똑같은 결과(검산용으로 넣음)
        visited[startNode] = true;
        dfs(startNode, 0);
        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }

    public static int dfs(int node, int currentWeight){
        //기저조건: 최종 노드에 도달
        // 최종노드라면 graph에 부모노드만 존재-> size가 0, 그리고 부모로부터 내려왔으니 부모는이미 방문한 노드
        Node parentNode = graph[node].get(0);
        if(graph[node].size()==1 && visited[parentNode.end])  return currentWeight;


        int largest = 0;
        int secondLargest=0;

        for(Node nextNode:graph[node]){
            if(!visited[nextNode.end]){
                //아직 방문 안한 노드면
                visited[nextNode.end] = true;
                int newWeight = dfs(nextNode.end, nextNode.weight);
                if(newWeight>=largest){
                    secondLargest = largest;
                    largest = newWeight;
                }else if(newWeight>=secondLargest){
                    secondLargest = newWeight;
                }
            }
        }

        ret = Integer.max(ret, largest+secondLargest);
        return largest+currentWeight;
    }

}
