package baekjoon.from1to10;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p7_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[N];
        for(int i=0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        if(N<=2){ // 예외처리: 갯수가 너무 적으면 그냥 0 리턴
            bw.write("0");
            bw.close();
            br.close();
            return;
        }
        Arrays.sort(arr);


        int ret = 0;
        for(int i = 0; i<N; i++){
            int left = 0;
            int right = N-1;
            while(left<right){
                long sum = arr[left] + arr[right];
                if(sum>arr[i]){
                    right-=1;
                }else if(sum<arr[i]){
                    left +=1;
                }else{
                    if(left!=i && right!=i){
                        ret+=1;
                        break;
                    }else if(left==i){
                        left+=1;
                    }else if(right==i){
                        right-=1;
                    }

                }
            }
        }
        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }
}
