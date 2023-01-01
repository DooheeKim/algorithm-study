package baekjoon.from41to50;

import java.io.*;
import java.util.Arrays;

public class p41_11689 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long inputNum = Long.parseLong(br.readLine());

        //에라토스테네스 체를 이용한 소수 구하기
        boolean[] primeNum = new boolean[(int)(Math.sqrt(inputNum)+1)];
        Arrays.fill(primeNum, true);
        primeNum[1] = false;
        for(int i=2; i<primeNum.length; i++){
            if(!primeNum[i]) continue;
            for(int j = i*2; j<primeNum.length; j+=i){
                primeNum[j] = false;
            }
        }


        // Euler phi(n) = n \Mul_p{1=(1/p)} where p = n의  소인수 집합
        long phi = inputNum;
        long inputNumDivided = inputNum;
        for(int i=2; i<primeNum.length; i++){
            if(!primeNum[i]) continue;// 소수가 아니면 필요없음
            if(inputNumDivided%i == 0){
                // i 가 inputNum의 소인수이면
                while(inputNumDivided%i==0){
                    inputNumDivided /= i;
                }
                phi=(phi-phi/i);
            }
        }
        if(inputNumDivided!=1){
            // 마지막 남은애가 소인수
            phi=(phi-phi/inputNumDivided);
        }
        bw.write(Long.toString(phi));
//        System.out.println(phi);
        bw.close();
        br.close();
    }
}
