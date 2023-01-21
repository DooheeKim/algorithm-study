package baekjoon.from61to70;

import java.io.*;
import java.util.*;

public class p67_11725 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int nodeCount = Integer.parseInt(br.readLine());
        List<Integer>[] tree = new ArrayList[nodeCount+1];
        int[] result = new int[nodeCount+1]; // 부모노드 정보를 저장할 결과 리스트
        for(int i=1; i<=nodeCount; i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0; i<nodeCount-1; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            //부모자식 순서 상관없이 주니까 양방향으로 넣기
            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        bfs(1, tree, result);
        for(int i=2; i<=nodeCount; i++){
            bw.write(Integer.toString(result[i]));
            bw.write("\n");
        }
        bw.close();
        br.close();
    }

    private static void bfs(int startNode, List<Integer>[] tree, int[] result){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        result[startNode]=-1; // 시작노드는 부모가 없어요, 0이면 방문처리에 안걸려서 임의로 다른값으로
        while(!queue.isEmpty()){
            int currentNode = queue.poll();
            for(int nextNode: tree[currentNode]){
                if(result[nextNode]==0){
                    result[nextNode] = currentNode;
                    queue.add(nextNode);
                }
            }
        }
    }
}
