package baekjoon.from11to20;

import java.io.*;
import java.util.*;

public class p11_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int dataSize = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<dataSize;i++){
            queue.add(Integer.parseInt(br.readLine()));
        }
        Stack<Integer> stack = new Stack<>();
        int i=1;
        List<Character> ret = new ArrayList<>(dataSize*2);
        while(i<=dataSize){
            //1,2,3,4,..., dataSize 까지 스택에 순서대로 일단 추가해야
            // 추가시엔 return값에 + 적어주는것도 잊지 않기
            stack.add(i);
            ret.add('+');
            //stack의 맨 끝 값이 queue의 맨 앞과 동일하면 --> 양쪽 다 pop
            // return 값에 - 적어주기 잊지 않기
            while(stack.size()>0 && stack.peek().equals(queue.peek())){
                stack.pop();
                queue.poll();
                ret.add('-');
            }
            i++;
        }
        if(stack.size()>0){
            bw.write("NO");
        }else{
            for(int j=0; j<ret.size();j++){
                bw.write(ret.get(j));
                if(j!=ret.size()-1) bw.write("\n");
            }
        }
        bw.close();
        br.close();


    }
}
