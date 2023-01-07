package baekjoon.from41to50;

import java.io.*;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class p49_2251 {
    static int[][] cache;
    static int[] maxCapacity = new int[3];
    static SortedSet<Integer> answer = new TreeSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        maxCapacity[0] = Integer.parseInt(st.nextToken()); //A
        maxCapacity[1] = Integer.parseInt(st.nextToken()); //B
        maxCapacity[2] = Integer.parseInt(st.nextToken()); //C
        cache = new int[maxCapacity[2]+1][maxCapacity[2]+1]; // 캐시: 물양+1 --> 0~꽉찬거까지
        // cache[A][B] 라고 하자. -- 물 양을 넘어서는거는 불가능하니까

        dfs(new int[]{0,0,maxCapacity[2]});
        for(Integer a:answer){
            bw.write(Integer.toString(a));
            bw.write(" ");
        }
        bw.close();
        br.close();
    }
    public static void dfs(int[] currentState){
        if(currentState[0]==0) answer.add(currentState[2]);
        for(int i = 0; i<3; i++){
            if(currentState[i]!=0){
                //나머지 두 개에 나눠주기
                int[] nextState = new int[3];
                int total = currentState[i]+currentState[(i+1)%3];
                if(total<=maxCapacity[(i+1)%3]) {
                    nextState[(i + 1) % 3] = total;
                    nextState[i] = 0;
                }else{
                    nextState[(i+1)%3] = maxCapacity[(i+1)%3];
                    nextState[i] = total-nextState[(i+1)%3];
                }
                nextState[(i+2)%3] = currentState[(i+2)%3];
                if(cache[nextState[0]][nextState[1]] == 0){
                    cache[nextState[0]][nextState[1]] = 1;
                    dfs(nextState);
                }

                nextState = new int[3];
                total = currentState[i]+currentState[(i+2)%3];
                if(total<=maxCapacity[(i+2)%3]) {
                    nextState[(i + 2) % 3] = total;
                    nextState[i] = 0;
                }else{
                    nextState[(i+2)%3] = maxCapacity[(i+2)%3];
                    nextState[i] = total-nextState[(i+2)%3];
                }
                nextState[(i+1)%3] = currentState[(i+1)%3];

                if(cache[nextState[0]][nextState[1]] == 0){
                    cache[nextState[0]][nextState[1]] = 1;
                    dfs(nextState);
                }

            }
        }
    }
}
