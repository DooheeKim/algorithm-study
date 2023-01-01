package baekjoon.from31to40;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p40_1016_mine {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // minVal 이상 maxVal 이하
        long minVal = Long.parseLong(st.nextToken());
        long maxVal = Long.parseLong(st.nextToken());

        // 에라토스테네스 체를 이용해서 sqrt(maxVal) 까지의 제곱수가 아닌 수들 구하기
        boolean[] nonSquareArr = new boolean[(int)Math.sqrt(maxVal)+1];
        Arrays.fill(nonSquareArr, true);
        for(int i=2; i<nonSquareArr.length; i++){
            // 1로 나눠지는건 제곱수가 아니라 패스하고 2부터 시작
            if(!nonSquareArr[i]) continue;
            for(int j=i*i; j<nonSquareArr.length; j*=i){
                nonSquareArr[j] = false;
            }
        }

        int range = (int)(maxVal-minVal)+1;
        boolean[] squareNoNoArr = new boolean[range];
        Arrays.fill(squareNoNoArr, true);

        for(int i=2; i<nonSquareArr.length; i++){
            if(!nonSquareArr[i]) continue;
            int square = i*i;

            // i^2 부터 2*i^2, 3*i^2, .... 다 false로 바꿔야됨
            // 근데 범위 내에 있는 수만 바꿔야됨
            for(long proportion = minVal/square; proportion<=maxVal/square; proportion++){
                int idx = (int)(proportion*square-minVal);
                if(idx<0) continue;
                squareNoNoArr[idx]=false;
            }
        }
        int counter = 0;
        for(int i=0; i<squareNoNoArr.length; i++){
            if(squareNoNoArr[i]) {
//                System.out.println(i+1);
                counter++;
            }
        }


        bw.write(Integer.toString(counter));
        bw.close();
        br.close();
    }
}
