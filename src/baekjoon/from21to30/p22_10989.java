package baekjoon.from21to30;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class p22_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrSize = Integer.parseInt(br.readLine());
        short arr[] = new short[arrSize];
        for(int i=0; i<arrSize;i++){
            arr[i] = Short.parseShort(br.readLine());
        }


        int divider = 1; //1, 10, 100... 이런식으로 계속 증가시키면서 나눠야 n번째자리 계산 가능
        short[] tmp = new short[arrSize];
        for(int i=1; i<=5; i++){
            int[] bucket = new int[10];

            //최대 10,000 까지 정렬해야 하므로..
            for(int j=0; j<arrSize; j++){
                bucket[(arr[j]/divider)%10]++;
            }
            for(int j=1; j<10; j++){
                bucket[j] += bucket[j-1];
            }


            for(int j=arrSize-1; j>=0; j--){
                tmp[bucket[(arr[j]/divider)%10]-1] = arr[j];
                bucket[(arr[j]/divider)%10]--;
            }
            for(int j=0; j<arrSize; j++){
                arr[j] = tmp[j];
            }

            divider *= 10;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<arrSize; i++){
            stringBuilder.append(arr[i]);
            stringBuilder.append("\n");
        }
        bw.write(stringBuilder.toString());
        bw.close();
        br.close();

    }
}
