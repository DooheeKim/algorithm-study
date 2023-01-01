package baekjoon.from1to10;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class p9_12891 {
    private static boolean doesMeet(Map<Character, Integer> required, Map<Character, Integer> current){
        //조건 만족하나 체크
        for(Character key:required.keySet()){
            if(required.get(key)>current.get(key)) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalLength = Integer.parseInt(st.nextToken());
        int partialLength = Integer.parseInt(st.nextToken());

        String givenString = br.readLine();
        st = new StringTokenizer(br.readLine()); // 세번째 줄 -> 읽어서 필요한 문자 수 저장
        Map<Character, Integer> requireMap = new HashMap<>();
        requireMap.put('A', Integer.parseInt(st.nextToken()));
        requireMap.put('C', Integer.parseInt(st.nextToken()));
        requireMap.put('G', Integer.parseInt(st.nextToken()));
        requireMap.put('T', Integer.parseInt(st.nextToken()));

        //초기화
        Map<Character, Integer> currentMap = new HashMap<>();
        currentMap.put('A',0);
        currentMap.put('C',0);
        currentMap.put('G',0);
        currentMap.put('T',0);

        // 진짜 문제풀이 시작
        int ret = 0;
        //초기조건
        for(int i =0 ; i<partialLength; i++){
            currentMap.merge(givenString.charAt(i), 1, Integer::sum);
        }
        for(int i = 1; i<totalLength-partialLength+1; i++){
            // 현재 map 검사
            if(doesMeet(requireMap, currentMap)) ret+=1;
            // 맨 앞에거 하나 빼고, 맨 뒤에거 하나 더하기
            currentMap.merge(givenString.charAt(i-1), -1, Integer::sum);
            currentMap.merge(givenString.charAt(i+partialLength-1), 1, Integer::sum);
        }
        //맨 마지막 검사 누락
        if(doesMeet(requireMap, currentMap)) ret+=1;

        bw.write(Integer.toString(ret));
        bw.close();
        br.close();
    }
}
