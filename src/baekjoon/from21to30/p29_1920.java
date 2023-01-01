package baekjoon.from21to30;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p29_1920 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int arrSize = Integer.parseInt(br.readLine());
        int[] arr = new int[arrSize];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<arrSize; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int valueArrSize = Integer.parseInt(br.readLine());
        int[] valueArr = new int[valueArrSize];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<valueArrSize; i++){
            valueArr[i] = Integer.parseInt(st.nextToken());
        }
        String exists = "1\n";
        String nonExists = "0\n";
        for(int i=0; i<valueArrSize; i++){
            int result = binarySearch(arr, 0, arrSize, valueArr[i]);
            if(result>=0){
                sb.append(exists);
            }else{
                sb.append(nonExists);
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();


    }
    public static int binarySearch(int[] arr, int start, int end, int val){
        //기저조건
        int mid = (start+end)/2;
        if(start==mid) return (arr[start]==val?start:-1);
        if(val>=arr[mid]){
            return binarySearch(arr, mid, end, val);
        }else{
            return binarySearch(arr, start, mid, val);
        }
    }
}
