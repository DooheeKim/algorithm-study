package baekjoon.from1to10;

import java.io.*;
import java.util.StringTokenizer;

public class p4_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 첫번째 줄: 데이터 갯수와 문제 갯수 받음
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numData = Integer.parseInt(st.nextToken());
        int numQuestion = Integer.parseInt(st.nextToken());

        //어레이 삽입하는 부분
        //누적 합 어레이도 같이 구하자
        int[][] orgArr = new int[numData][numData];
        int[][] sumArr = new int[numData][numData];
        for(int i=0; i<numData; ++i){
            st = new StringTokenizer(br.readLine());//array에 들어갈 거 읽음
            for(int j=0; j<numData; ++j){
                orgArr[i][j] = Integer.parseInt(st.nextToken());

                int sumUp = (i==0?0:sumArr[i-1][j]);
                int sumLeft = (j==0?0:sumArr[i][j-1]);
                int sumUpLeft = (i==0 || j==0 ?0:sumArr[i-1][j-1]);

                sumArr[i][j] = orgArr[i][j]+sumUp+sumLeft-sumUpLeft;
            }
        }

        //정답 뽑아내는 부분
        for(int i=0; i<numQuestion;i++){
            st = new StringTokenizer(br.readLine());
            int row1 = Integer.parseInt(st.nextToken())-1;
            int col1 = Integer.parseInt(st.nextToken())-1;

            int row2 = Integer.parseInt(st.nextToken())-1;
            int col2 = Integer.parseInt(st.nextToken())-1;

            int ret = sumArr[row2][col2];
            ret = ret-(row1==0?0:sumArr[row1-1][col2]);
            ret = ret-(col1==0?0:sumArr[row2][col1-1]);
            ret = ret+(col1==0||row1==0 ? 0 :sumArr[row1-1][col1-1]);
            bw.write(Integer.toString(ret));
            bw.write("\n");
        }
        br.close();
        bw.close();
    }

}
