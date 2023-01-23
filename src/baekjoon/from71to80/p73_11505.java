package baekjoon.from71to80;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p73_11505 {
    private static int leafCount;
    private static int DIVIDER = 1_000_000_007;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int modifyCount = Integer.parseInt(st.nextToken());
        int partialMultiply = Integer.parseInt(st.nextToken());

        leafCount = (int)Math.pow(2, Math.ceil(Math.log(nodeCount)/Math.log(2)));
        long[] tree = new long[leafCount*2];
        Arrays.fill(tree, 1); // 곱이니까 1로 초기화

        int idx = leafCount;
        for(int i=0; i<nodeCount;i++){
            tree[idx] = Integer.parseInt(br.readLine())%DIVIDER;
            idx++;
        }

        // 트리 채우기
        idx = leafCount*2-1;
        while(idx>1){
            long right = tree[idx];
            long left = tree[--idx];
            tree[idx/2] = (left*right)%DIVIDER;
            --idx;
        }

        for(int i=0; i <modifyCount+partialMultiply; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int in1 = Integer.parseInt(st.nextToken());
            int in2 = Integer.parseInt(st.nextToken());

            if(type==1){
                update(in1, in2, tree);
            }else if(type==2){
                bw.write(Integer.toString(getMultiplication(in1, in2, tree)));
                bw.write('\n');
            }
        }
        bw.close();
        br.close();

    }
    private static void update(int idx, int val, long[] tree){
        //idx를 트리인덱스로 바꾸기
        idx = idx + leafCount - 1;
        tree[idx] = val%DIVIDER;

        while(idx>=2){
            if(idx%2 == 1){
                tree[idx/2] = (tree[idx-1]*tree[idx])%DIVIDER;
            }else{
                tree[idx/2] = (tree[idx+1]*tree[idx])%DIVIDER;
            }
            idx/=2;
        }
//        while(idx>1){
//            idx /= 2;
//            tree[idx] = tree[idx*2]%DIVIDER * tree[idx*2+1]%DIVIDER;
//        }
    }

    private static int getMultiplication(int fromIdx, int toIdx, long[] tree){
        fromIdx = fromIdx + leafCount - 1;
        toIdx = toIdx + leafCount -1;

        long result = 1;
        while(fromIdx<=toIdx){
            if(fromIdx%2==1){
                result = result*tree[fromIdx]%DIVIDER;
                fromIdx += 1;
            }
            if(toIdx%2 == 0){
                result = result*tree[toIdx]%DIVIDER;
                toIdx -= 1;
            }
            fromIdx /= 2;
            toIdx /= 2;

        }

        return (int)(result);
    }
}
