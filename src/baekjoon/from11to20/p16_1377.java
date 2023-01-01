package baekjoon.from11to20;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class p16_1377 {
    private static class Tuple implements Comparable<Tuple>{
        int idx;
        int val;

        private Tuple(int idx, int val){
            this.idx = idx;
            this.val = val;
        }
        @Override
        public int compareTo(Tuple o) {
            if(this.val == o.val) return Integer.compare(this.idx, o.idx);
            return Integer.compare(this.val, o.val);
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int dataSize = Integer.parseInt(br.readLine());
        PriorityQueue<Tuple> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0; i<dataSize; i++){
            priorityQueue.add(new Tuple(i,Integer.parseInt(br.readLine())));
        }

        int prevIdx = -1; // 초기화. 행렬 최대 크기보다 크게
        int ret = 0; //결과

        for(int i=0; i<dataSize; i++){
            Tuple polled = priorityQueue.poll();
            if(polled.idx>prevIdx){
                //여기 걸린다는건, 값이 작은게 큰 값보다 더 뒤에 있다는 뜻 -> 정렬이 안된거 만남
                ret = i+1; //1회에서 시작해야돼서 1더함
            }
            prevIdx = polled.idx;
        }
        bw.write(Integer.toString(ret));
        bw.close();
        br.close();

    }
}
