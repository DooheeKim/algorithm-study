package baekjoon.from51to60;

import java.io.*;
import java.util.StringTokenizer;

public class p51_1976 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int countCities = Integer.parseInt(br.readLine());
        int countPaths = Integer.parseInt(br.readLine());
        int[] parent = new int[countCities+1];
        for(int i=0; i<=countCities; i++){
            // 초기화
            parent[i] = i;
        }
        for(int i=1; i<=countCities; i++){
            // adjacency matrix로 행렬을 표현하는데.. M[i, j] = { 0 || 1 }로 연결관계 표현
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=countCities; j++){
                int connection = Integer.parseInt(st.nextToken());
                if(connection==1){
                    uniteNodes(parent, i, j);
                }
            }
        }
//        for(int a:parent){
//            System.out.print(a);
//            System.out.print(" ");
//        }


        // 전부 같은 부모인지 확인하기
        st = new StringTokenizer(br.readLine());
        int n1 = findParent(parent, Integer.parseInt(st.nextToken()));
        while(st.hasMoreTokens()){
            int n2 = findParent(parent,Integer.parseInt(st.nextToken()));
            if(n1!=n2){
                bw.write("NO");
                bw.close();
                br.close();
                return;
            }
        }
        bw.write("YES");
        bw.close();
        br.close();
    }

    // Union-find 구현
    // 1. 부모찾기
    public static int findParent(int[] parent, int node){
        if(parent[node]==node) return node;
        return parent[node] = findParent(parent, parent[node]);
    }
    // 2. Union
    public static void uniteNodes(int[] parent, int node1, int node2){
        node1 = findParent(parent, node1);
        node2 = findParent(parent, node2);
        if(node1<node2) parent[node2] = node1;
        else parent[node1] = node2;

        /* 기존 내가 쓴거: 현재노드의 부모만 바꿔줌. 답안: 부모노드의 소속을 바꿔줌
        int parent1 = findParent(parent, node1);
        int parent2 = findParent(parent, node2);
        if(parent1<parent2){
            parent[node2] = parent1;
        }else{
            parent[node1] = parent2;
        }
         */
    }
    // 3. Find
    public static boolean checkSameGroup(int[] parent, int node1, int node2){
        int parent1 = findParent(parent, node1);
        int parent2 = findParent(parent, node2);
        return parent1==parent2;
    }
}
