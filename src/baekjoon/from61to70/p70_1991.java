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
        char[] tree = new char[treeSize+1]; // A가 1, B가 2... 이래야돼서 +1
        Map<Character, Integer> alphaNumericMap = new HashMap<>();
        alphaNumericMap.put('A', 1);
        for(int i=1; i<=nodeCount; i++){
            st = new StringTokenizer(br.readLine());
            // st 세번 읽어서 각각 부모, 왼쪽자식, 오른쪽자식 노드가 된다
            char self = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            alphaNumericMap.put(self, i);
            alphaNumericMap.put(left, i*2);
            alphaNumericMap.put(right, i*2+1);
        }
    }
}
