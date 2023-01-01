package baekjoon.from31to40;

import java.io.*;
import java.util.PriorityQueue;

public class p33_1715 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrSize = Integer.parseInt(br.readLine());


        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0; i<arrSize;i++){
            priorityQueue.add(Integer.parseInt(br.readLine()));
        }

        int ret = 0;
        while(priorityQueue.size()>1){
            int element1 = priorityQueue.poll();
            int element2 = priorityQueue.poll();
            ret += (element1+element2);
            priorityQueue.add(element1+element2);
        }

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }
}
