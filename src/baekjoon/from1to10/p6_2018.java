package baekjoon.from1to10;

import java.io.*;

public class p6_2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int left = 0; int right=1;

        int ret = 1;// 결과값. 기본으로 맨 끝에 하나(N)는 무조건 있음
        int[] arr = new int[N+1];
        //arr 초기화
        for(int i=0; i<N;i++){
            arr[i] = i+1; //arr에 순서대로 1, 2, 3, 4, ....N 채우기
        }
        arr[N] = 0;

        //left~right까지의 합을 저장할 변수
        //sum = arr[0]으로 초기화
        long sum = arr[left]+arr[right];
        while(right<N && left<right){
            if(sum<N){
                ++right;
                sum+=arr[right];
            }
            else if(sum>N){
                sum -= arr[left];
                ++left;
            }
            else{ // sum == N 인 경우
                ret+=1;
                sum -= arr[left];
                ++left;
                ++right;
                sum += arr[right];
            }
        }

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }
}
