package baekjoon.from61to70;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p69_14425_sort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int setSize = Integer.parseInt(st.nextToken());
        int givenSize = Integer.parseInt(st.nextToken());
        int ret = 0;

        String[] set = new String[setSize];
        String[] compareArr= new String[givenSize];
        for(int i=0; i<setSize; i++){
            set[i] = br.readLine();
        }
        Arrays.sort(set);

        for(int i=0; i<givenSize; i++){
            compareArr[i] = br.readLine();
        }
        Arrays.sort(compareArr);

        int setIdx = 0;
        int compareIdx = 0;
        while(setIdx<setSize && compareIdx<givenSize){
            int comp = set[setIdx].compareTo(compareArr[compareIdx]);
            if(comp==0){
                ret++;
//                setIdx++;
                compareIdx++;
            }else if(comp>0){
                compareIdx++;
            }else{
                setIdx++;
            }
        }

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }
}
