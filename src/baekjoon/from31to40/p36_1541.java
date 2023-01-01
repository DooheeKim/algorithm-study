package baekjoon.from31to40;

import java.io.*;

public class p36_1541 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String originalExpression = br.readLine();
        String[] expressionSeparatedWithMinus = originalExpression.split("-");

        // 스트링으로 분리된걸 int로 바꿔서 계산한 결과값을 저장할 어레이
        int[] partialCalculatedDividedExpression = new int[expressionSeparatedWithMinus.length];

        for(int i=0; i<expressionSeparatedWithMinus.length; i++){
            String[] expressionSeparatedWithPlus = expressionSeparatedWithMinus[i].split("\\+");
            int calculated = 0;
            for(String num:expressionSeparatedWithPlus){
                calculated+=Integer.parseInt(num);
            }
            partialCalculatedDividedExpression[i] = calculated;
        }

        int ret = 0;
        for(int i=0; i<partialCalculatedDividedExpression.length; i++){
            if(i==0) ret+=partialCalculatedDividedExpression[i];
            else{
                ret -= partialCalculatedDividedExpression[i];
            }
        }
        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }
}
