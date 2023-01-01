package baekjoon.from11to20;

import java.io.*;
import java.util.StringTokenizer;

public class p19_11004 {
    static int[] arr;
    static int K;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int arrLength = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); //K 번째로 작은 수
        // arr 채워넣기
        arr = new int[arrLength];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<arrLength; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        quickSort(0, arrLength-1); //arrLength-1이 마지막 인덱스이자 pivot이 되므로

        bw.write(Integer.toString(arr[K-1]));
//        for(int i=0; i<arrLength;i++){
//            System.out.println(arr[i]);
//        }
        bw.close();
        br.close();
    }
    // 공부용 퀵소트 구현 - 전역 array 인 arr을 직접 수정
    public static void quickSort(int start, int end){
        if(start>=end) return;
        int pivot = partition(start, end);
        if(pivot==K-1){
            return;
        }else if(pivot>K-1){
            quickSort(start, pivot-1);
        }else{
            quickSort(pivot+1, end);
        }
    }
    public static int partition(int start, int end){
        int lo = start;
        int hi = end;
        int pivot = arr[end];

        while(lo<hi){
            while(arr[lo]<pivot & lo<hi) lo++;
            while(arr[hi]>=pivot & lo<hi) hi--;
            swap(lo, hi);
        }
        swap(end, hi);
        return hi;
    }
    public static void swap(int idx1, int idx2){
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
