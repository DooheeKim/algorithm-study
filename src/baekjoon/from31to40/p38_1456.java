package baekjoon.from31to40;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p38_1456 {
    public static void main(String[] args) throws IOException{
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

        // 이제 Array에 거의소수 추가하기
        int almostPrimeCounter = 0;
        double startLog = Math.log(start);
        double endLog = Math.log(end);
        boolean isStartAlmostPrime = false;
        for(int i=2; i< primeArr.length;i++){
            boolean prime = primeArr[i];
            if(!prime) continue;

            double logI = Math.log(i); // prime의 로그
            /// 만약에 startLog 가 2.332512이고 endLog가 6.2452 이라면 -> 3, 4, 5, 6 네개 (6-2)
            /// 근데 startLog가 2라면 -> 2, 3, 4, 5, 6 다섯개
            //
            almostPrimeCounter += ((int)(endLog/logI)-(int)(startLog/logI));
            // 예외처리
            // i 가 이미 start< i < end 인 경우 제외
            if(i>start){
                almostPrimeCounter-=1;
            }
            if(!isStartAlmostPrime && (startLog/logI)>1 && ((startLog/logI)-(int)(startLog/logI))<1e-12){
                isStartAlmostPrime = true;
            }
        }
        if(isStartAlmostPrime) almostPrimeCounter+=1;
        bw.write(Integer.toString(almostPrimeCounter));
        bw.close();
        br.close();
    }
}
