package baekjoon.from61to70;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class p69_14425 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int setSize = Integer.parseInt(st.nextToken());
        int givenSize = Integer.parseInt(st.nextToken());
        int ret = 0;
        Node rootNode = new Node(' ');
        for(int i=0; i<setSize; i++){
            Node node = rootNode;
            char[] charArr = br.readLine().toCharArray();
            for(char character:charArr){
                if(node.next[character-'a']!=null){
                    // 다음 글자가 이미 있으면 -> 다음 노드로 이동
                    node = node.next[character-'a'];
                }else{
                    // 다음 글자가 안들어가 있으면 -> 추가해야됨
                    Node newNode = new Node(character);
                    node.next[character-'a'] = newNode;
                    node = newNode;
                }
            }
            //루프를 빠져나왔을 때 node: 마지막 글자
            node.isEnd = true;
        }

        // 트리 돌면서 단어 있나 검사
        for(int i=0; i<givenSize; i++){
            Node node = rootNode;
            char[] charArr = br.readLine().toCharArray();
            boolean isContained = true;
            for(char character:charArr){
                if(node.next[character-'a']!=null){
                    node = node.next[character-'a'];
                }else{
                    isContained = false;
                    break;
                }
            }

            if(node.isEnd && isContained) ret++;
        }

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }

    private static class Node{
        char val;
        Node[] next;
        boolean isEnd;
        private Node(char val){
            this.val = val;
            this.isEnd = false;
            next = new Node[26];
        }
    }
}
