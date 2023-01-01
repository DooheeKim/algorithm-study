package baekjoon.from41to50;

import java.io.*;
import java.util.StringTokenizer;

public class p42_1934 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int numCases = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<numCases; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int gcd = getGcd(n1,n2);
            int lcm = n1*n2/gcd;
            sb.append(lcm);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static int getGcd(int a, int b){
        if(a<b) return getGcd(b,a);
        if(a%b==0) return b;
        return getGcd(b, a%b);
    }
}
