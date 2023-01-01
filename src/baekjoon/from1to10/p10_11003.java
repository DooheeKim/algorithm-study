package baekjoon.from1to10;

import java.io.*;
import java.util.*;

public class p10_11003 {
    private static class Tuple implements Comparable<Tuple>{
        public int idx;
        public int val;
        private Tuple(int idx, int val){
            this.idx = idx;
            this.val = val;
        }
        @Override
        public int compareTo(final Tuple given){
            if (val == given.val) return Integer.compare(idx, given.idx);
            return Integer.compare(val, given.val);
        }
    }
    private static void insertToWindow(Deque<Tuple> window, Tuple toAdd){
        while(window.size()>0 && window.peekLast().compareTo(toAdd)>=0){
            window.removeLast();
        }
        window.addLast(toAdd);
    }
    private static Tuple getSmallestFromWindow(Deque<Tuple> window, int windowSize, int currIdx){
        while(window.size()>0 && window.peekFirst().idx < currIdx-windowSize+1){
            window.removeFirst();
        }
        return window.peekFirst();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int dataSize = Integer.parseInt(st.nextToken());
        int windowSize = Integer.parseInt(st.nextToken());
        int[] arr = new int[dataSize];
        int[] ret = new int[dataSize];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<dataSize; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //DEQUE 로 윈도우 관리
        Deque<Tuple> window = new LinkedList<>();
        for(int i=0; i<dataSize; i++){
            insertToWindow(window, new Tuple(i, arr[i]));
            ret[i] = getSmallestFromWindow(window, windowSize, i).val;
        }
        for(int i=0; i<dataSize; i++){
            bw.write(ret[i]+" ");
        }
        bw.close();
        br.close();




    }
}
