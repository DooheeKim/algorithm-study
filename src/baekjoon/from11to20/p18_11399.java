package baekjoon.from11to20;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p18_11399 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] waitTimeArr = new int[N];
        for(int i=0; i<N; i++){
            waitTimeArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(waitTimeArr);

        // 사용자별 걸린 시간 구하기
        int[] waitTimeAccumulationArr = new int[N];
        waitTimeAccumulationArr[0] = waitTimeArr[0];
        long totalWaitTime=waitTimeArr[0];
        for(int i=1; i<N; i++){
            waitTimeAccumulationArr[i] = waitTimeAccumulationArr[i-1] + waitTimeArr[i];
            totalWaitTime+=waitTimeAccumulationArr[i];
        }
        bw.write(Long.toString(totalWaitTime));
        bw.close();
        br.close();
    }
}
