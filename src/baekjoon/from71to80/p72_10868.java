package baekjoon.from71to80;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p72_10868 {
    static int leafCount;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int numCount = Integer.parseInt(st.nextToken());
        int pairCount = Integer.parseInt(st.nextToken());

        leafCount = (int)Math.pow(2, Math.ceil(Math.log(numCount)/Math.log(2)));
        int[] tree = new int[leafCount*2];
        Arrays.fill(tree, 1_000_000_001); // 최솟값 구하는 문제니까 최댓값으로 채우기

        int idx = leafCount;
        for(int i = 0; i <numCount; i++){
            tree[idx] = Integer.parseInt(br.readLine());
            idx++;
        }

        // 거꾸로 돌면서 부모노드까지 채우기
        for(int i = 2*leafCount-1; i>1; i--){
            // 2번까지만 돌아서 2/2 = 1 번 인덱스까지만 채워야됨, 0번인덱스는 버리는애
            tree[i/2] = Math.min(tree[i/2], tree[i]);
        }

        for(int i = 0; i<pairCount; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(partialMin(from, to, tree)));
            bw.write("\n");
        }
        bw.close();
        br.close();
    }

    private static int partialMin(int from, int to, int[] tree){
        // 주어진 인덱스를 트리 인덱스로 변환
        from = from + leafCount - 1;
        to = to + leafCount - 1;

        int min = 1_000_000_001;
        while(from<=to){
            if(from==to) {
                min = Math.min(min, tree[from]);
                break;
            }
            if(from%2 == 1){
                min = Math.min(min, tree[from]);
                from++;
            }
            from /= 2;

            if(to%2 == 0){
                min = Math.min(min, tree[to]);
                to--;
            }
            to /= 2;
        }
        return min;
    }
}
