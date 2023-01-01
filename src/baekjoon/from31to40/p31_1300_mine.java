package baekjoon.from31to40;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class p31_1300_mine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrSize = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        //예외처리
        if(k==1){
            bw.write("1");
            bw.close();
            br.close();
            return;
        }

        int[] arrAppeared = new int[k+1];// k 보다 작은 숫자들이 몇번씩 출현 했는지
        for(long i=1; i<=arrSize; i++){
            for(long j=1; j<=arrSize; j++){
                long val = i*j;
                if(val>k) break;
                arrAppeared[(int)val] += 1;
            }
        }
        // arrAccumulated 사이사이에는 빈공간이 있어서 binarySearch로 찾기 힘듬
        // ex) N이 3이면 1 2 3 4 6 9 --> 5, 7, 8 에 대한 부분은 0임
        // 0인 부분 쪼여 없애기 && 누적값으로 바꾸기
        // {값, 지금까지의 누적 호출횟수}
        List<int[]> appearanceAccumulationList = new ArrayList<>();
        appearanceAccumulationList.add(new int[]{0,0});
        int accIdx = 0;
        int appIdx = 0;
        for(int appearance : arrAppeared){
            if(appearance!=0){
                appearanceAccumulationList.add(new int[]{appIdx,
                        appearanceAccumulationList.get(accIdx)[1]+appearance});
                accIdx++;
            }
            appIdx++;
        }
        int ret = binarySearch(appearanceAccumulationList, 0,
                appearanceAccumulationList.size(), k);

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }

    private static int binarySearch(List<int[]> arrList, int start, int end, int k){
        //기저조건: k의 값이 arrList.get(mid-1) 보다는 크고 arrList.get(mid)보다는 작거나 같으면 mid 리턴
        int mid = (start+end)/2;
        if(arrList.get(mid-1)[1]<k && arrList.get(mid)[1]>=k) return arrList.get(mid)[0];

        if(arrList.get(mid)[1]>=k){
            return binarySearch(arrList,start, mid ,k);
        }else{
            return binarySearch(arrList,mid+1, end,k);
        }
    }
}
