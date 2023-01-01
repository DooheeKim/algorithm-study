package baekjoon.from31to40;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p39_1747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long start = Long.parseLong(st.nextToken());
        long end = Long.parseLong(st.nextToken());

        // end의 sqrt까지 에라토스테네스의 체를 이용한 소수 어레이 만들기
        boolean[] primeArr = new boolean[(int)Math.sqrt(end)+1];
        Arrays.fill(primeArr, true);

        primeArr[1]=false; // 1은 소수가 아님
        for(int i=2; i<primeArr.length;i++){
            boolean prime = primeArr[i];
            if(prime){
                for(int j=2*i; j<primeArr.length; j+=i){
                    primeArr[j] = false;
                }
            }
        }


        bw.close();
        br.close();
    }
}
