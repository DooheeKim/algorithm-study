package part1.ch08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PI {
    private final int INF = 99999999;
    String str;
    int[] cache;
    public PI(String str){
        this.str = str;
        cache = new int[10002];
        Arrays.fill(cache, -1);
    }
    int classify(int a, int b){
        //숫자 조각 가져오기
        String subStr = str.substring(a, b);
        // 전부 동일한 글자이면 난이도 1점
        Set<Character> letterSet = new HashSet();
        for(int i=0; i<subStr.length();i++){
            letterSet.add(subStr.charAt(i));
        }
        if (letterSet.size()==1) return 1;

        // 등차수열인지 아닌지 판별
        boolean progressive = true;
        for(int i = 0; i<subStr.length()-1; i++){
            if(subStr.charAt(i+1)-subStr.charAt(i)!= subStr.charAt(1)-subStr.charAt(0)){
                progressive = false;
            }
        }
        //단조증가하는지 아닌지 판별
        if(progressive && Math.abs(subStr.charAt(1)-subStr.charAt(0))==1) return 2;

        //두 수가 번갈아 등장하는지 등장
        boolean alternating = true;
        for(int i = 0; i < subStr.length(); i++){
            if(subStr.charAt(i)!=subStr.charAt(i%2)) alternating=false;
        }
        if(alternating) return 4;
        if(progressive) return 5;

        return 10; // 나머지
    }

    int memorize(int begin){
        //기저사례: 끝에 이미 도달
        if(begin==str.length()) return 0;

        int ret = cache[begin];
        if(ret!=-1) return ret;
        cache[begin] = INF;
        for(int L = 3; L<=5; L++){
            if(begin+L <= str.length())
                cache[begin] = Integer.min(cache[begin], memorize(begin+L) + classify(begin, begin+L));
        }
        ret = cache[begin];
        return ret;
    }
}
