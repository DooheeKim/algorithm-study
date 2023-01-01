package baekjoon.from31to40;

import java.io.*;
import java.util.StringTokenizer;

public class p32_11047 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numCoinSpecies = Integer.parseInt(st.nextToken());
        int targetPrice = Integer.parseInt(st.nextToken());

        int[] coinSpecies = new int[numCoinSpecies];
        for(int i=0; i<numCoinSpecies; i++){
            coinSpecies[i] = Integer.parseInt(br.readLine());
        }
        int ret = 0;
        for(int i=numCoinSpecies-1; i>=0; i--){
            if(coinSpecies[i]<=targetPrice){
                ret+=(targetPrice/coinSpecies[i]);
                targetPrice %= coinSpecies[i];
            }
        }

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }
}
