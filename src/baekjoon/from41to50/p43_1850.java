package baekjoon.from41to50;

import java.io.*;
import java.util.StringTokenizer;

public class p43_1850 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long gcd = getGcd(a,b);
        for(long i = 0; i<gcd; i++){
            bw.write('1');
        }
        bw.close();
        br.close();
    }

    private static long getGcd(long a, long b){
        if(a<b) return getGcd(b,a);
        // 기저조건
        if(a%b==0) return b;
        return getGcd(b, a%b);
    }
}
