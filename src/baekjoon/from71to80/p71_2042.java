package baekjoon.from71to80;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

public class p71_2042 {
    static int leafCount;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int numCount = Integer.parseInt(st.nextToken()); //N
        int modifyCount = Integer.parseInt(st.nextToken()); //M
        int partialSumCount = Integer.parseInt(st.nextToken()); //K

        leafCount = (int) Math.pow(2, Math.ceil(Math.log(numCount)/Math.log(2))); ///이진트리의 리프노드 갯수
        long[] tree = new long[leafCount*2];

        int idx = leafCount;
        for(int i=0; i<numCount; i++){
            tree[idx] = Long.parseLong(br.readLine());
            idx++;
        }

        // tree 부분합으로 채우기
        idx = leafCount*2-1; // tree의 맨 뒤에서 부터
        while(idx>1){
            long right = tree[idx];
            long left = tree[--idx];
            long partialSum = right+left;

            tree[idx/2] = partialSum;
            idx--;
        }

        for(int i=0; i<modifyCount+partialSumCount; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int in1 = Integer.parseInt(st.nextToken());
            long in2 = Long.parseLong(st.nextToken());

            if(type==1){
                updateNode(in1, in2, tree);
            }
            else if(type==2){
                long psum = partialSum(in1, (int)in2, tree);
                bw.write(Long.toString(psum));
                bw.write("\n");
            }
        }
        bw.close();
        br.close();
    }

    static void updateNode(int idx, long val, long[] tree){
        //업데이트 -- 부모까지 다 업데이트 쳐야됨
        // idx번째 수를 tree의 index로 변환
        idx = idx+leafCount-1;

        long diff = val-tree[idx]; // 기존 값과 새로 들어온 값의 차이만큼 구간합 업데이트
        while(idx>1){
            tree[idx]+=diff;
            idx/=2;
        }
    }
    static long partialSum(int from,  int to, long[] tree){
        //부분합 구하기
        // tree index 로 변환
        from = from + leafCount - 1;
        to = to + leafCount - 1;
        long sum = 0;

        while(from<=to){
            if(from==to){
                sum += tree[from];
                break;
            }
            if(from%2 == 1){
                sum += tree[from];
                from += 1;
            }
            from /= 2;

            if(to%2 == 0){
                sum += tree[to];
                to -= 1;
            }
            to /=2;
        }
        return sum;
    }
}
