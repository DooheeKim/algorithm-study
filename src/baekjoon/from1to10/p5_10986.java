package baekjoon.from1to10;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p5_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arrLength = Integer.parseInt(st.nextToken());
        int mod = Integer.parseInt(st.nextToken());

        //array 읽기
        st = new StringTokenizer(br.readLine());
        long[] sumArr = new long[arrLength];
        long[] remainder = new long[arrLength];
        long[] countArr = new long[mod];
        Arrays.fill(countArr, 0);
        long ret = 0;

        for(int i=0; i<arrLength; i++){
            sumArr[i] = (i==0?0:sumArr[i-1])+Integer.parseInt(st.nextToken());
            remainder[i] = sumArr[i]%mod;
            countArr[(int)remainder[i]] += 1;
            if(remainder[i]==0) ret+=1;
        }
        for(int i=0; i<mod; i++){
            if(countArr[i]>1){
                ret += countArr[i]*(countArr[i]-1)/2;
            }
        }


        bw.write(Long.toString(ret));
        bw.close();
        br.close();
    }
}
