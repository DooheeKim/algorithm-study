package part1.ch08;

import java.util.Arrays;

//이거는 그냥 어려워서 생각도 못했음.
//나는 글자단위로 쪼개고 인덱스를 저장한 map을 만들어야한다고 생각했는데
// * 단위로 쪼개갖고 아예 접근부터가 달랐음
public class WildCard {
    // -1은 아직 캐시가 계산되지 않았음
    // 1은 해당 입력들이 서로 대응됨
    // 0 은 해당 입력들이 서로 대응되지 않음
    int[][] cache;
    String W, S;
    public WildCard(String W, String S){
        cache = new int[101][101];
        for(int i=0; i<cache.length; i++){
            Arrays.fill(cache[i], -1);
        }
        this.W = W;
        this.S = S;
    }
    boolean match(String W, String S){
        // w: wildcard, s: 비교하고자하는 스트링
        // w[pos]와 s[pos]를 맞춰나간다.
        int pos = 0;
        while(pos<W.length() && pos<S.length() &&
                (W.charAt(pos)==S.charAt(pos) || W.charAt(pos)=='?')){
            ++pos;
        }
        //더이상 대응할 수 없으면 왜 while문이 끝났는지 확인해야 한다.
        // 패턴의 끝에 도달한 경우: 문자열도 끝났는지 확인해야됨
        if(pos == W.length()) return pos==S.length();
        //*를 만나서 끝난 경우: *에 몇글자를 대응시켜야 할 지 재귀호출로 확인한다.
        if(W.charAt(pos)=='*'){
            for(int skip=0; pos+skip<=S.length(); skip++){
                if(match(W.substring(pos+1), S.substring(pos+skip))){
                    return true;
                }
            }
        }
        return false;
    }
    boolean match(){
        return match(this.W,this.S);
    }

    boolean matchMemoized(int w, int s){
        int ret = cache[w][s];
        if(ret!=-1) return ret==1;

        while(s<S.length() && w<W.length()
                && (W.charAt(w)=='?' || W.charAt(w)==S.charAt(s))){
            ++w;
            ++s;
        }
        if(w == W.length()){
            ret = (s == S.length()? 1:0);
            cache[w][s] = ret;
            return ret==1;
        }
        if(W.charAt(w)=='*'){
            for(int skip=0; skip+s<=S.length(); ++skip){
                if(matchMemoized(w+1, s+skip)){
                    cache[w][s] = 1;
                    return true;
                }
            }
        }
        return false;
    }
    boolean matchMemoized(){
        return matchMemoized(0,0);
    }

}
