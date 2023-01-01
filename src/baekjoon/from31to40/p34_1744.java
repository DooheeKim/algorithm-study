package baekjoon.from31to40;

import java.io.*;
import java.util.Arrays;

public class p34_1744 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrSize = Integer.parseInt(br.readLine());
        int[] arr = new int[arrSize];
        for(int i=0; i<arrSize; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        if(arrSize == 1) {
            bw.write(Integer.toString(arr[0]));
            bw.close();
            br.close();
            return;
        }
        Arrays.sort(arr);

        int leftIdx = 0;
        int rightIdx = arrSize-1;
        int ret = 0;

        while(leftIdx<arrSize && arr[leftIdx]<=0){
            if(leftIdx+1<arrSize && arr[leftIdx+1]<=0){
                ret+=(arr[leftIdx]*arr[leftIdx+1]);
                leftIdx+=2;
            }else{
                ret+=arr[leftIdx];
                leftIdx+=1;
            }
        }

        while(rightIdx>=0 && arr[rightIdx]>0){
            if(rightIdx-1>=0 && arr[rightIdx-1]>1){
                ret += (arr[rightIdx]*arr[rightIdx-1]);
                rightIdx -= 2;
            }else{
                ret += arr[rightIdx];
                rightIdx -= 1;
            }
        }

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }
}
