package part1.ch08;

import java.util.Arrays;

public class Polyomino {
    private final int MOD = 10*1000*1000;
    int[][] cache;
    
    public Polyomino(){
        this.cache = new int[101][101];
        for(int i=0; i<this.cache.length; ++i){
            Arrays.fill(this.cache[i], -1);
        }
    }
    // n개의 정사각형으로 이루어졌고 맨위 가로줄에 first개의
    // 정사각형을 포함하는 폴리오미노의 수를 반환
    int poly(int n, int first){
        //기저사례
        if(n==first) return 1;

        //메모이제이션
        if(cache[n][first]!=-1) return cache[n][first];
        cache[n][first]=0;
        for(int second=1; second<=n-first; ++second){
            int add = (first==0? 1: second+first-1);
            add *= poly(n-first, second);
            add %= MOD;
            cache[n][first]+=add;
            cache[n][first] %= MOD;
        }
        return cache[n][first];
    }

}
