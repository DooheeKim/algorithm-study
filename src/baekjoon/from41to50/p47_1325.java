package baekjoon.from41to50;

import java.io.*;
import java.util.*;

public class p47_1325 {
    static boolean[] visited;
    static int[] trusted;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int countNodes = Integer.parseInt(st.nextToken());
        int countEdges = Integer.parseInt(st.nextToken());

        //그래프 초기화
        List<Integer>[] graph = new ArrayList[countNodes+1];
        for(int i=1; i<=countNodes; i++){
            graph[i] = new ArrayList<Integer>();
        }
        visited = new boolean[countNodes+1];
        trusted = new int[countNodes+1];


        for(int i=0; i<countEdges;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //a 가 b를 신뢰한다 -> 신뢰를 많이 받을수록 좋다
            graph[a].add(b);
        }

        for(int node=1; node<=countNodes; node++){
            dfs(graph, node);
            Arrays.fill(visited, false);
        }

        int maxTrusted = 0;

        for(int v=1; v<=countNodes; v++){
            if(trusted[v]>maxTrusted) maxTrusted=trusted[v];
        }
        for(int v=1; v<=countNodes; v++){
            if(trusted[v]==maxTrusted){
                bw.write(Integer.toString(v));
                bw.write(" ");
            }
        }

        bw.close();
        br.close();
    }
    private static void dfs(List<Integer>[] graph, int node){
        //기저조건: 더이상 방문할 노드가 없으면 0 리턴
        for(int to:graph[node]){
            if(!visited[to]){
                //방문 이력이 없으면
                visited[to] = true;
                trusted[to]+=1;
                dfs(graph, to);

            }
        }
    }
    public static void bfs(List<Integer>[] graph, int node){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        visited[node] = true;
        while(!queue.isEmpty()){
            int poll =queue.poll();
            for(int to: graph[poll]){
                if(!visited[to]){
                    visited[to] = true;
                    trusted[to]+=1;
                    queue.add(to);
                }
            }
        }
    }
}
