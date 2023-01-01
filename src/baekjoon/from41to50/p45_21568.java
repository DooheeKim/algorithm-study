package baekjoon.from41to50;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class p45_21568 {
    private static Stack<int[]> stack;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        stack = new Stack<>();//[몫, 나머지]의 스택
        int gcd = extendedGcd(A,B);
        if(C%gcd!=0){
            bw.write("-1");
            bw.close();
            br.close();
            return;
        }
        int K = C/gcd;

        // 초깃값
        int x = 1;
        int y = 0;

        while(!stack.isEmpty()){
            int[] arr = stack.pop();
            int tmp = y;
            y = x - y * arr[0];
            x = tmp;
        }

        bw.write(Integer.toString(K*x));
        bw.write(" ");
        bw.write(Integer.toString(K*y));

        bw.close();
        br.close();
    }
    public static int extendedGcd(int a, int b){
        if(a%b==0){
            stack.push(new int[]{a/b, a%b});
            return b;
        }
        stack.push(new int[]{a/b, a%b});
        return extendedGcd(b, a%b);
    }
}
