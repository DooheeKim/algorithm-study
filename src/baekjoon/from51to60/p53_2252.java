package baekjoon.from51to60;

import java.io.*;
import java.util.*;

public class p53_2252 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int studentCount = Integer.parseInt(st.nextToken());
        int comparisonCount = Integer.parseInt(st.nextToken());

        int[] inDegreeArr = new int[studentCount+1];
        List<Integer>[] graph = new ArrayList[studentCount+1];
        for(int i=1; i<=studentCount; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<comparisonCount; i++){
            st = new StringTokenizer(br.readLine());
            int shorter = Integer.parseInt(st.nextToken());
            int larger = Integer.parseInt(st.nextToken());

            graph[shorter].add(larger);
            inDegreeArr[larger]+=1;
        }
        Queue<Integer> queue = new LinkedList<>();

        // queue에 초기값 채우기
        for(int i=1; i<=studentCount; i++){
            if(inDegreeArr[i]==0){
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder(); // 결과를 저장할 string builder
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(Integer nextParticipant: graph[node]){
                inDegreeArr[nextParticipant]--;
                if(inDegreeArr[nextParticipant]==0){
                    queue.add(nextParticipant);
                }
            }
            sb.append(node);
            sb.append(" ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
