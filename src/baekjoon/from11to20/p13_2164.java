package baekjoon.from11to20;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class p13_2164 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        //초기화
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++){
            queue.add(i);
        }

        while(queue.size()>1){
            //첫장 버리기
            queue.remove();
            if(queue.size()==1){
                break;
            }
            int nextBottom = queue.remove();
            queue.add(nextBottom);
        }
        bw.write(queue.peek().toString());
        bw.close();
        br.close();
    }
}
