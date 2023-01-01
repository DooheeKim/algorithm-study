package baekjoon.from11to20;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class p17_1427 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String numString = br.readLine();
        String[] numCharList = numString.split("");
        Arrays.sort(numCharList, Collections.reverseOrder());

        for(int i=0; i< numCharList.length; i++){
            bw.write(numCharList[i]);
        }
        bw.close();
        br.close();
    }
}
