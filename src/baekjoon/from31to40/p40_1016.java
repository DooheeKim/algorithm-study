package baekjoon.from31to40;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p40_1016 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // minVal 이상 maxVal 이하
        long minVal = Long.parseLong(st.nextToken());
        long maxVal = Long.parseLong(st.nextToken());

        // 에라토스테네스 체를 이용해서 sqrt(maxVal) 까지의 제곱수가 아닌 수들 구하기
        boolean[] nonSquareArr = new boolean[(int)(maxVal-minVal+1)];

        for(long i=2; i*i<=maxVal; i++){
            long pow = i*i;
            long startIdx = minVal/pow;
            if(minVal%pow!=0) startIdx++; // 나머지가 있으면 1을 더해야 minVal보다 큰 제곱수에서 시작함

            for (long j=startIdx; j*pow<=maxVal; j++){
                nonSquareArr[(int) (j*pow-minVal)] = true;
            }
        }
        int counter = 0;
        for(int i=0; i<=maxVal-minVal; i++){
            if(!nonSquareArr[i]) counter++;
        }

        bw.write(Integer.toString(counter));
        bw.close();
        br.close();
    }
}
