package baekjoon.from11to20;

import java.io.*;
import java.util.Arrays;

public class p20_2751 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrLength = Integer.parseInt(br.readLine());
        int[] arr = new int[arrLength];
        for(int i=0; i<arrLength;i ++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        arr = mergeSort(arr);
        for(int i=0; i<arrLength;i ++){
            bw.write(Integer.toString(arr[i])+"\n");
        }
        bw.close();
        br.close();
    }
    public static int[] mergeSort(int[] arr){
        if(split(arr)<0) return arr;

        int[] arr1 = mergeSort(Arrays.copyOfRange(arr, 0, split(arr)));
        int[] arr2 = mergeSort(Arrays.copyOfRange(arr, split(arr), arr.length));

        return merge(arr1, arr2);
    }
    public static int split(int[] arr){
        if(arr.length==1) return -1;
        return arr.length/2;
    }
    public static int[] merge(int[] arr1, int[] arr2){
        int i = 0;
        int j = 0;
        int k = 0;
        int[] merged = new int[arr1.length+arr2.length];
        while(i< arr1.length && j < arr2.length){
            if(arr1[i]<=arr2[j]){
                merged[k] = arr1[i];
                i++;
            }else{
                merged[k] = arr2[j];
                j++;
            }
            k++;
        }
        //남은거 처리
        while(i<arr1.length){
            merged[k] = arr1[i];
            i++;
            k++;
        }
        while(j<arr2.length){
            merged[k] = arr2[j];
            j++;
            k++;
        }
        return merged;
    }
}
