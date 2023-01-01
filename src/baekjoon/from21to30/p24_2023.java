package baekjoon.from21to30;

import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class p24_2023 {
    static int minVal;
    static int maxVal;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        minVal = (int)Math.pow(10,N-1);
        maxVal = (int)Math.pow(10,N);

        dfs(2);
        dfs(3);
        dfs(5);
        dfs(7);
        bw.write(sb.toString());
        bw.close();
        br.close();

    }
    private static boolean isPrime(Integer input){
//        BigInteger num = new BigInteger(String.valueOf(input));
//        return num.isProbablePrime(1);
        for(int i=2; i<= Math.sqrt(input); i++){
            if(input%i==0) return false;
        }
        return true;
    }

    private static void dfs(Integer input){
        //기저조건: 자리수 만족하면 결과 리턴
        if(input>=minVal && input<maxVal){
            if(isPrime(input)){
                sb.append(input);
                sb.append("\n");
            }
        }
        for(int i = 1; i<10;i+=2){
            //홀수만 불러서 dfs 시키기
            if(i==5) continue; //5로 끝나는건 5의 배수니까 패스
            int nextInt = input*10+i;
            if(isPrime(nextInt)){
                //소수인지 확인하고 결과에 따라 dfs 진행
                dfs(nextInt);
            }
        }
    }
}
