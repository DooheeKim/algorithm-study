package baekjoon.from61to70;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class p70_1991 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int nodeCount = Integer.parseInt(br.readLine());
        int treeSize = (int)Math.pow(2, (int)Math.ceil(Math.log(nodeCount+1)/Math.log(2)))-1; // ex) 노드가 5개여도 트리 저장공간은 2^3-1개짜리가 필요하다
        char[][] tree = new char[treeSize][3];

        for(int i=1; i<=nodeCount; i++){
            st = new StringTokenizer(br.readLine());
            // st 세번 읽어서 각각 부모, 왼쪽자식, 오른쪽자식 노드가 된다
            char self = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree[self-'A'][0] = self;
            if(left!='.') tree[self-'A'][1] = left;
            if(right!='.') tree[self-'A'][2] = right;
        }
        StringBuilder sb = new StringBuilder();
        preorderDfs('A', tree, sb);
        bw.write(sb.toString());
        bw.write("\n");

        sb = new StringBuilder();
        inorderDfs('A',tree, sb);
        bw.write(sb.toString());
        bw.write("\n");

        sb = new StringBuilder();
        postorderDfs('A', tree, sb);
        bw.write(sb.toString());

        bw.close();
        br.close();

    }
    public static void preorderDfs(char startNode, char[][] tree, StringBuilder sb){
        sb.append(startNode);
        if(tree[startNode-'A'][1]!=0) {
            preorderDfs(tree[startNode-'A'][1], tree, sb);
        }
        if(tree[startNode-'A'][2]!=0){
            preorderDfs(tree[startNode-'A'][2], tree, sb);
        }
    }
    public static void inorderDfs(char startNode, char[][]tree, StringBuilder sb){
        if(tree[startNode-'A'][1]!=0) {
            inorderDfs(tree[startNode-'A'][1], tree, sb);
        }
        sb.append(startNode);
        if(tree[startNode-'A'][2]!=0){
            inorderDfs(tree[startNode-'A'][2], tree, sb);
        }
    }

    public static void postorderDfs(char startNode, char[][]tree, StringBuilder sb){
        if(tree[startNode-'A'][1]!=0) {
            postorderDfs(tree[startNode-'A'][1], tree, sb);
        }

        if(tree[startNode-'A'][2]!=0){
            postorderDfs(tree[startNode-'A'][2], tree, sb);
        }
        sb.append(startNode);
    }
}
