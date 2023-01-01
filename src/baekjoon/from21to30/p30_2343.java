package baekjoon.from21to30;

import java.io.*;
import java.util.StringTokenizer;

public class p30_2343 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numLectures = Integer.parseInt(st.nextToken());
        int numDisks = Integer.parseInt(st.nextToken());
        int[] arr = new int[numLectures];

        st = new StringTokenizer(br.readLine());
        //arr에 넣음과 동시에 최댓값과 총합을 이용해 start와 end의 범위 구하기
        int max = 0;
        int sum = 0;
        for(int i=0; i<numLectures; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum+=arr[i];
            max = Integer.max(max, arr[i]);
        }

        int ret = binarySearch(arr, max, sum, numDisks);
        bw.write(Integer.toString(ret));
        bw.close();
        br.close();


    }
    private static int binarySearch(int[] arr, int startSize, int endSize, int numDisksLimit){
        if(startSize>endSize)  return startSize;
        int midSize = (startSize+endSize)/2;
//        System.out.println(midSize);
//        if(midSize==startSize) return startSize;

        int diskUsage = 0;
        int numDisks = 1;

        // disk에 영상 넣어보기
        for(int i=0; i<arr.length; i++){
            if(diskUsage+arr[i]<=midSize){
                diskUsage+=arr[i];
            }else{
                //disk 사용량이 초과하면
                diskUsage = arr[i];
                numDisks+=1;
            }
        }
        if(numDisks>numDisksLimit){
            return binarySearch(arr, midSize+1, endSize, numDisksLimit);
        }else{
            return binarySearch(arr, startSize, midSize-1, numDisksLimit);
        }


    }


}
