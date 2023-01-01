package baekjoon.from31to40;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p37_1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] arr = new boolean[end+1]; //0,1,2, ..., end 까진데 1~end 까지만 저장
        Arrays.fill(arr, true);

        //1은 소수가 아니므로 false 로 처리, 2부터 시작
        arr[1] = false;
        for(int i=2; i<= Math.sqrt(arr.length); i++){
            boolean prime = arr[i];
            if(prime==false) continue;
            for(int j = 2*i; j<arr.length; j+=i){
                arr[j]=false;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=start; i<=end; i++){
            if(arr[i]){
                sb.append(i);
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
