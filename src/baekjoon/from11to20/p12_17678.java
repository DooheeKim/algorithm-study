package baekjoon.from11to20;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class p12_17678 {
    private static class Tuple{
        int idx;
        int value;
        private Tuple(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int arrSize = Integer.parseInt(br.readLine());
        //array 읽기
        Tuple[] arr = new Tuple[arrSize];
        int[] ret = new int[arrSize];
        Arrays.fill(ret, -1); // 미리 -1로 초기화해두면 루프 다돌고 stack에 남은거 다시 채울필요 없음
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<arrSize;i++){
            arr[i] = new Tuple(i, Integer.parseInt(st.nextToken()));
        }

        //Stack을 이용해 Tuple 객체 관리
        Stack<Tuple> stack = new Stack<>();
        for(int i=0; i<arrSize; i++){
            //현재 스택이랑 arr[i]랑 값 비교한다음, arr[i].val이 더 크면 ret에 arr[i].val 넣기
            while(stack.size()>0 && stack.peek().value<arr[i].value){
                ret[stack.pop().idx] = arr[i].value;
            }
            //while문 끝나면 스택에 arr[i] 넣기
            stack.push(arr[i]);
        }
        // 프린트하기
        for(int i=0; i<arrSize; i++){
            bw.write(ret[i]+" ");
        }
        bw.close();
        br.close();
    }
}
