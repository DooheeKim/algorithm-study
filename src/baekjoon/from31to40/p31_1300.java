package baekjoon.from31to40;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class p31_1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrSize = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        //예외처리
        if(k==1){
            bw.write("1");
            bw.close();
            br.close();
            return;
        }


        int ret = binarySearch(arrSize, 1, k, k);

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }

    private static int binarySearch(int arrSize, int start, int end, int k){
        //기저조건: start>end 인데 start보다 작은 element가 k개보다 많으면 return start
        if(start>end){
            return start;
        }
        int mid = (start+end)/2;
        if(getNumberOfSmallerElements(arrSize,mid)<k){
            return binarySearch(arrSize, mid+1, end, k);
        }else{
            return binarySearch(arrSize, start, mid-1, k);
        }
    }
    private static int getNumberOfSmallerElements(int arrSize, int k){
        int ret = 0;
        for(int i=1; i<=arrSize; i++){
            ret += Integer.min(k/i, arrSize);
        }
        return ret;
    }
}
