package baekjoon.from61to70;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class p68_1068 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int nodeCount = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[nodeCount];
        List<Integer>[] tree = new ArrayList[nodeCount];
        for(int i=0; i<nodeCount; i++){
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int rootNode=-1;
        for(int i=0;i<nodeCount;i++){
            int node = Integer.parseInt(st.nextToken());
            if(node==-1){
                rootNode = i;
                continue;
            }
            tree[node].add(i);
        }
        int deleteNode = Integer.parseInt(br.readLine());
        visited[deleteNode] = true; // 삭제할 노드를 이미 방문처리

        int ret = dfs(rootNode, tree, visited);
        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }

    private static int dfs(int node, List<Integer>[] tree, boolean[] visited){
        // 연습삼아 stack으로 한번 해볼까
        if(visited[node]==true) return 0; // 예외처리: 루트노드를 제거한 경우엔 0 리턴
        Stack<Integer> stack = new Stack<>();
        stack.add(node);
        visited[node] = true;
        int leafCount = 0;
        while(!stack.isEmpty()){
            int currNode = stack.pop();
            boolean isLeaf = true;
            for(int nextNode:tree[currNode]){
                if(!visited[nextNode]){
                    stack.add(nextNode);
                    visited[nextNode] = true;
                    isLeaf = false;
                }
            }
            if(isLeaf){
                leafCount++;
            }
        }
        return leafCount;
    }
}
