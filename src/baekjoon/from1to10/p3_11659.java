package baekjoon.from1to10;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p3_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numData = Integer.parseInt(st.nextToken());
        int numQuestion = Integer.parseInt(st.nextToken());

        int[] data;
        int[] sumData = new int[numData];
        data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        sumData[0] = data[0];
        //구간합 구하기
        for(int i=1; i<numData; ++i){
            sumData[i] = data[i]+sumData[i-1];
        }

        //실제 정답 구하기
        for(int i=0; i<numQuestion; ++i){
            StringTokenizer inTokenizer = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(inTokenizer.nextToken());
            int to =  Integer.parseInt(inTokenizer.nextToken());

            bw.write(Integer.toString(sumData[to-1]-(from==1?0:sumData[from-2])));
            bw.write("\n");
        }

        br.close();
        bw.close();
    }
}
