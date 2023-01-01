package baekjoon.from11to20;

import java.io.*;
import java.util.PriorityQueue;

public class p14_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOperation = Integer.parseInt(br.readLine());
        int[] operationArr = new int[numOperation];
        for(int i=0; i<numOperation;i++){
            operationArr[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2)->{
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);
            if(first_abs==second_abs){
                return Integer.compare(o1, o2);
            }else{
                return Integer.compare(first_abs, second_abs);
            }
        });
        for(int i=0; i<numOperation;i++){
            if(operationArr[i]!=0){
                priorityQueue.add(operationArr[i]);
            }else{
                Integer poll = priorityQueue.poll();
                bw.write((poll==null?"0":Integer.toString(poll)) + "\n");
            }
        }
        bw.close();
        br.close();
    }
}
