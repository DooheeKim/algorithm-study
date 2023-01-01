package part1.ch09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalcLIS {
    int lis(List<Integer> A){
        if(A.size()==0) return 0;
        int ret=0;

        for(int i=0; i<A.size(); i++){
            List<Integer> B = new ArrayList<>();
            for(int j=i+1; j<A.size(); j++){
                if(A.get(i)<A.get(j)){
                    B.add(A.get(j));
                }
            }
            ret = Integer.max(ret, 1+lis(B));
        }
        return ret;
    }

    int n;
    int[] cache, S;
    int[] choices;
    public CalcLIS(){}
    public CalcLIS(int[] S){
        this.S = S;
        n = this.S.length;
        this.cache = new int[100];
        this.choices = new int[100];
        Arrays.fill(this.cache, -1);
        Arrays.fill(this.choices, -1);
    }

    int lis2(int start){
        int ret = cache[start];
        if(ret != -1) {
            return ret;
        }
        ret = 1;
        for (int next=start+1; next<n; ++next){
            if(S[start]<S[next]){
                cache[start] = Integer.max(ret,lis2(next)+1);
            }
            ret = Integer.max(ret, cache[start]);
        }
        return ret;
    }

}
