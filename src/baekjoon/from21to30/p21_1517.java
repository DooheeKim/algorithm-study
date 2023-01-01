package baekjoon.from21to30;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p21_1517 {
    static long counter = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrSize = Integer.parseInt(br.readLine());
        int[] arr = new int[arrSize];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<arrSize; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(arr);
        bw.write(Long.toString(counter));
        bw.close();
        br.close();
    }

    public static int[] mergeSort(int[] arr){
        //기저조건: 종료시점
        if(arr.length==1) return arr;
        int splitIdx = arr.length/2;

        int[] arr1 = mergeSort(Arrays.copyOfRange(arr, 0, splitIdx));
        int[] arr2 = mergeSort(Arrays.copyOfRange(arr, splitIdx, arr.length));

        return merge(arr1, arr2);
    }
    public static int[] merge(int[] arr1, int[] arr2){
        int i = 0;
        int j = 0;
        int k = 0;
        int[] ret = new int[arr1.length+arr2.length];
        while(i<arr1.length && j<arr2.length){
            if(arr1[i]<arr2[j]){
                ret[k] = arr1[i];
                if(k<i){//왼쪽으로 이동한 애들만 더하면됨
                    counter += (i-k);
                }
                i++;
            }else if(arr1[i]>arr2[j]){
                ret[k] = arr2[j];
                if(k<j+arr1.length){
                    counter += (j+arr1.length-k);
                }
                j++;
            }else{
                ret[k] = arr2[j];
                j++;
            }
            k++;
        }
        while(i<arr1.length){
            ret[k] = arr1[i];
            i++;
            k++;
        }
        while(j<arr2.length){
            ret[k] = arr2[j];
            j++;
            k++;
        }
        return ret;
    }
}
