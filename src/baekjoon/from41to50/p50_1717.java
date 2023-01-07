package baekjoon.from41to50;

import java.io.*;
import java.util.StringTokenizer;

public class p50_1717 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int countNodes = Integer.parseInt(st.nextToken());
        int countOperations = Integer.parseInt(st.nextToken());
        int[] parent = new int[countNodes+1];
        for(int i=0; i<=countNodes; i++){
            //초기화: 자기 자신이 parent
            parent[i] = i;
        }

        for(int i=0; i<countOperations;i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            if(op==0){
                //union operation
                union(parent, node1, node2);
            }else{
                //0또는 1로 주어지니까 여기는 무조건 1
                //find operation
                if(find(parent, node1, node2)){
                    bw.write("YES");
                }else{
                    bw.write("NO");
                }
                bw.write("\n");
            }
        }
        bw.close();
        br.close();

    }

    public static void union(int[] parent, int node1, int node2){
        node1 = whoIsParent(parent, node1);
        node2 = whoIsParent(parent, node2);
        if(node1<node2) parent[node2] = node1;
        else parent[node1] = node2;
    }
    public static int whoIsParent(int[] parent, int node){
        if(parent[node]==node) return node;
        return parent[node] = whoIsParent(parent, parent[node]);
    }
    public static boolean find(int[] parent, int node1, int node2){
        int parent1 = whoIsParent(parent, node1);
        int parent2 = whoIsParent(parent, node2);
        return parent1==parent2;
    }
}
