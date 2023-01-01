package baekjoon.from21to30;

import java.io.*;
import java.util.*;

public class p23_11724 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numNodes = Integer.parseInt(st.nextToken());
        int numEdges = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        //initialization
        for(int i=1; i<=numNodes; i++){
            graph.put(i, new ArrayList<Integer>());
        }

        //엣지 읽기
        for(int i=0; i<numEdges; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            //양방향 그래프
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        boolean[] visited= new boolean[numNodes+1];//노드번호 1번부터 시작하도록
        Stack<Integer> stack = new Stack<>();
        int numConnectedComponent = 0;
        for(int node=1; node<=numNodes; node++){
            if(visited[node]) continue;
            stack.push(node);
            numConnectedComponent++;
            while(!stack.isEmpty()){
                int visitNode = stack.pop();
                if(!visited[visitNode]){
                    visited[visitNode] = true;
                    stack.addAll(graph.get(visitNode));
                }
            }
        }
        bw.write(Integer.toString(numConnectedComponent));
        bw.close();
        br.close();

    }
}
